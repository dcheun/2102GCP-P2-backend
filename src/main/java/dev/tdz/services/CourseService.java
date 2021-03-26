package dev.tdz.services;

import dev.tdz.entities.Course;

import java.util.Set;

public interface CourseService {

    Course createCourse(Course course);

    Course getCourseById(int id);

    Set<Course> getAllCourses();

    Course updateCourse(Course course);

    boolean deleteCourseById(int id);

}
