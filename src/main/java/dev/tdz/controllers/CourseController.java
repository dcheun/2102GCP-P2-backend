package dev.tdz.controllers;

import dev.tdz.aspects.Authorized;
import dev.tdz.aspects.Logging;
import dev.tdz.entities.Course;
import dev.tdz.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@Component
@RestController
public class CourseController {
    @Autowired
    CourseService courseService;

    @Logging
    @GetMapping("/courses")
    public Set<Course> retrieveAllCourses() {
        return this.courseService.getAllCourses();
    }

    @Logging
    @GetMapping("/courses/{id}")
    public Course getCourseById(@PathVariable int id) {
        Course course = this.courseService.getCourseById(id);
        return course;
    }

    @Logging
    @PostMapping("/courses")
    public Course createCourse(@RequestBody Course course) {
        this.courseService.createCourse(course);
        return course;
    }

    @Logging
    @PutMapping("/courses/{id}")
    public Course updateCourse(@PathVariable int id, @RequestBody Course course) {
        course.setId(id);
        this.courseService.updateCourse(course);
        return course;
    }

    @Logging
    @DeleteMapping("/courses/{id}")
    public Boolean deleteCourseById(@PathVariable int id) {
        Boolean result = this.courseService.deleteCourseById(id);
        return result;
    }
}
