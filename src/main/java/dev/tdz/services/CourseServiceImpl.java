package dev.tdz.services;

import dev.tdz.aspects.ServiceLogging;
import dev.tdz.entities.Course;
import dev.tdz.exceptions.NotFoundException;
import dev.tdz.repos.CourseRepo;
import dev.tdz.utils.AppUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;


@Component
@Service
public class CourseServiceImpl implements CourseService {

    private static final Logger logger = Logger.getLogger(
            CourseServiceImpl.class.getName());

    @Autowired
    CourseRepo courseRepo;

    @ServiceLogging
    @Override
    public Course createCourse(Course course) {
        try {
            return this.courseRepo.save(course);
        } catch (Exception e) {
            AppUtil.logException(logger, e, "createCourse: Unable to create");
            throw e;
        }
    }

    @ServiceLogging
    @Override
    public Course getCourseById(int id) {
        try {
            return this.courseRepo.findById(id).get();
        } catch (NoSuchElementException e) {
            throw new NotFoundException("No such course exists");
        } catch (Exception e) {
            AppUtil.logException(logger, e,
                    "getCourseById: Unable to get record with id=" + id);
            throw e;
        }
    }

    @ServiceLogging
    @Override
    public Set<Course> getAllCourses() {
        try {
            return new HashSet<>(
                    (Collection<? extends Course>) this.courseRepo.findAll());
        } catch (Exception e) {
            AppUtil.logException(logger, e, "getAllCourses: Unable to retrieve");
            throw e;
        }
    }

    @ServiceLogging
    @Override
    public Course updateCourse(Course course) {
        try {
            return this.courseRepo.save(course);
        } catch (Exception e) {
            AppUtil.logException(logger, e, "updateCourse: Unable to update");
            throw e;
        }
    }

    @ServiceLogging
    @Override
    public boolean deleteCourseById(int id) {
        try {
            this.courseRepo.deleteById(id);
            return true;
        } catch (Exception e) {
            AppUtil.logException(logger, e,
                    "deleteCourseById: Unable to delete course with id=" + id);
            throw e;
        }
    }

}
