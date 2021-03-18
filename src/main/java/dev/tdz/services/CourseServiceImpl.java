package dev.tdz.services;

import dev.tdz.entities.Course;
import dev.tdz.repos.CourseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;


@Component
@Service
public class CourseServiceImpl implements CourseService{

    @Autowired
    CourseRepo courseRepo;


    @Override
    public Course createCourse(Course course) {

        return this.courseRepo.save(course);
    }

    @Override
    public Course getCourseById(int id) {
        Course course = this.courseRepo.findById(id).get();
        return course;
    }

    @Override
    public Set<Course> getAllCourses() {
        Set<Course> courses = new HashSet<Course>((Collection<? extends Course>) this.courseRepo.findAll());
        return courses;
    }

    @Override
    public Course updateCourse(Course course) {

        return this.courseRepo.save(course);
    }

    @Override
    public boolean deleteCourseById(int id) {
        this.courseRepo.deleteById(id);
        try {
            this.getCourseById(id);
            return false;
        } catch(NoSuchElementException e){
            return true;
        }

    }
}
