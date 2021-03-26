package dev.tdz.controllers;

import dev.tdz.aspects.Authorization;
import dev.tdz.aspects.ErrorHandler;
import dev.tdz.aspects.Logging;
import dev.tdz.entities.Course;
import dev.tdz.exceptions.NotAuthorizedException;
import dev.tdz.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Set;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@Component
@RestController
public class CourseController {

    @Autowired
    CourseService courseService;

    @Logging
    @ErrorHandler
    @GetMapping("/courses")
    public Set<Course> retrieveAllCourses() {
        return this.courseService.getAllCourses();
    }

    @Logging
    @ErrorHandler
    @GetMapping("/courses/{id}")
    public Course getCourseById(@PathVariable int id) {
        return this.courseService.getCourseById(id);
    }

    @Logging
    @Authorization
    @ErrorHandler
    @PostMapping("/courses")
    public Course createCourse(@RequestBody Course course) {
        HttpServletRequest request = (
                (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()
        ).getRequest();
        Integer authUserId = (Integer) request.getAttribute("authUserId");
        Integer authRoleId = (Integer) request.getAttribute("authRoleId");
        // Verify if user is an instructor.
        if (authRoleId != 1) {
            throw new NotAuthorizedException("Must be instructor to create courses");
        }
        // IMPORTANT: Set the instructor_id field of the Transient course object.
        course.setInstructorId(authUserId);
        course.setId(0);
        return this.courseService.createCourse(course);
    }

    @Logging
    @Authorization
    @ErrorHandler
    @PutMapping("/courses/{id}")
    public Course updateCourse(@PathVariable int id, @RequestBody Course course) {
        // Check existence of entity to be updated.
        Course currCourse = this.getCourseById(id);
        // Get JWT claims.
        HttpServletRequest request = (
                (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()
        ).getRequest();
        Integer authUserId = (Integer) request.getAttribute("authUserId");
        Integer authRoleId = (Integer) request.getAttribute("authRoleId");
        // Check for instructor.
        if (authRoleId != 1) {
            throw new NotAuthorizedException("Must be instructor to update course");
        }
        // Check if instructor is updating his/her own course.
        if (authUserId != currCourse.getInstructorId()) {
            throw new NotAuthorizedException("You can only update your own course");
        }
        // Override fields of Transient object.
        course.setInstructorId(authUserId);
        course.setId(id);
        return this.courseService.updateCourse(course);
    }

    @Logging
    @Authorization
    @ErrorHandler
    @DeleteMapping("/courses/{id}")
    public Boolean deleteCourseById(@PathVariable int id) {
        // Check existence of entity to be deleted.
        Course currCourse = this.getCourseById(id);
        // Get JWT claims.
        HttpServletRequest request = (
                (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()
        ).getRequest();
        Integer authUserId = (Integer) request.getAttribute("authUserId");
        Integer authRoleId = (Integer) request.getAttribute("authRoleId");
        // Check for instructor.
        if (authRoleId != 1) {
            throw new NotAuthorizedException("Must be instructor to delete course");
        }
        // Check if user is deleting his/her own course.
        if (authUserId != currCourse.getInstructorId()) {
            throw new NotAuthorizedException("You can only delete your own course");
        }
        return this.courseService.deleteCourseById(id);
    }

}
