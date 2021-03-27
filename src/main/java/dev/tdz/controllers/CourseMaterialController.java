package dev.tdz.controllers;

import dev.tdz.aspects.Authorization;
import dev.tdz.aspects.ErrorHandler;
import dev.tdz.aspects.Logging;
import dev.tdz.entities.Course;
import dev.tdz.entities.CourseMaterial;
import dev.tdz.exceptions.NotAuthorizedException;
import dev.tdz.services.CourseMaterialService;
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
public class CourseMaterialController {

    @Autowired
    CourseMaterialService courseMaterialService;

    @Autowired
    CourseService courseService;

    @Logging
    @ErrorHandler
    @GetMapping("/coursematerials")
    public Set<CourseMaterial> retrieveAllCourseMaterials() {
        return this.courseMaterialService.getAllCourseMaterials();
    }

    @Logging
    @ErrorHandler
    @GetMapping("/coursematerials/{id}")
    public CourseMaterial getCourseMaterialById(@PathVariable int id) {
        return this.courseMaterialService.getCourseMaterialById(id);
    }

    @Logging
    @Authorization
    @ErrorHandler
    @PostMapping("/coursematerials")
    public CourseMaterial createCourseMaterial(
            @RequestBody CourseMaterial courseMaterial
    ) {
        // Check existence of parent course.
        Course parentCourse = this.courseService.getCourseById(courseMaterial.getCourseId());
        // Get JWT claims.
        HttpServletRequest request = (
                (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()
        ).getRequest();
        Integer authUserId = (Integer) request.getAttribute("authUserId");
        Integer authRoleId = (Integer) request.getAttribute("authRoleId");
        // Verify user is an instructor.
        if (authRoleId != 1) {
            throw new NotAuthorizedException("Must be instructor to create materials");
        }
        // Check if this is intended for a course that belongs to the instructor.
        if (authUserId != parentCourse.getInstructorId()) {
            throw new NotAuthorizedException("You can only create materials for your own course");
        }
        // Override fields of Transient object.
        courseMaterial.setCourseId(parentCourse.getId());
        courseMaterial.setId(0);
        return this.courseMaterialService.createCourseMaterial(courseMaterial);
    }

    @Logging
    @Authorization
    @ErrorHandler
    @PutMapping("/coursematerials/{id}")
    public CourseMaterial updateCourseMaterial(
            @PathVariable int id,
            @RequestBody CourseMaterial courseMaterial
    ) {
        // Check existence of entities to be updated.
        CourseMaterial currCourseMaterial = this.getCourseMaterialById(id);
        Course parentCourse = this.courseService.getCourseById(currCourseMaterial.getCourseId());
        // Get JWT claims.
        HttpServletRequest request = (
                (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()
        ).getRequest();
        Integer authUserId = (Integer) request.getAttribute("authUserId");
        Integer authRoleId = (Integer) request.getAttribute("authRoleId");
        // Check for instructor.
        if (authRoleId != 1) {
            throw new NotAuthorizedException("Must be instructor to update materials");
        }
        // Check if instructor is updating his/her own material.
        if (authUserId != parentCourse.getInstructorId()) {
            throw new NotAuthorizedException("You can only update your own course material");
        }
        // Override fields of Transient object.
        courseMaterial.setCourseId(parentCourse.getId());
        courseMaterial.setId(id);
        return this.courseMaterialService.updateCourseMaterial(courseMaterial);
    }

    @Logging
    @Authorization
    @ErrorHandler
    @DeleteMapping("/coursematerials/{id}")
    public Boolean deleteCourseMaterialById(@PathVariable int id) {
        // Get JWT claims.
        HttpServletRequest request = (
                (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()
        ).getRequest();
        Integer authUserId = (Integer) request.getAttribute("authUserId");
        Integer authRoleId = (Integer) request.getAttribute("authRoleId");
        // Check for instructor.
        if (authRoleId != 1) {
            throw new NotAuthorizedException("Must be instructor to delete material");
        }
        return this.courseMaterialService.deleteCourseMaterialById(id);
    }

}
