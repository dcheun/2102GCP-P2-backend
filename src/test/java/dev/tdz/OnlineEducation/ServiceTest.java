package dev.tdz.OnlineEducation;

import dev.tdz.entities.AppUser;
import dev.tdz.entities.Course;
import dev.tdz.entities.CourseMaterial;
import dev.tdz.entities.Rating;
import dev.tdz.services.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;

@SpringBootTest
@Transactional
public class ServiceTest {

    @Autowired
    AppUserService appUserService;

    @Autowired
    CourseMaterialService courseMaterialService;

    @Autowired
    CourseService courseService;

    @Autowired
    RatingService ratingService;

    @Autowired
    UserRoleService userRoleService;

    @Test
    @Rollback
    public void create_app_user(){
        AppUser appUser = new AppUser(0,"Bill","Gates","bigman@hotmail.com","gates1",2);
        appUserService.createAppUser(appUser);
        System.out.println(appUser);
    }
    @Test
    @Rollback
    public void delete_app_user_by_id(){
        AppUser appUser = new AppUser(0,"John", "Smith","js.hotmail.com","jsss123",1);
        AppUser appUser1 = appUserService.createAppUser(appUser);
        int id = appUser1.getId();
        boolean result = appUserService.deleteAppUserById(id);
        System.out.println(result);

    }


    @Test
    @Rollback
    public void create_course_material(){
        CourseMaterial courseMaterial = new CourseMaterial(0,"video","Intro to managing your finance",1);
        courseMaterialService.createCourseMaterial(courseMaterial);
        System.out.println(courseMaterial);
    }

    @Test
    @Rollback
    public void delete_course_material_by_id(){
        CourseMaterial courseMaterial = new CourseMaterial(0,"video","Intro to managing your finance",1);
        CourseMaterial courseMaterial1 = courseMaterialService.createCourseMaterial(courseMaterial);
        int id = courseMaterial1.getId();
        boolean result = courseMaterialService.deleteCourseMaterialById(id);
        System.out.println(result);

    }

    @Test
    @Rollback
    public void create_course(){
        Course course = new Course(0,"Java course", "Learn full stack java", 6);
        courseService.createCourse(course);
        System.out.println(course);
    }

    @Test
    @Rollback
    public void delete_course_by_id(){
        Course course = new Course(0,"Java course", "Learn full stack java", 6);
        Course course1 = courseService.createCourse(course);
        int id = course1.getId();
        boolean result = courseService.deleteCourseById(id);
        System.out.println(result);
    }

    @Test
    @Rollback
    public void create_rating(){
        Rating rating = new Rating(0,4,"Good Course",3);
        ratingService.createRating(rating);
        System.out.println(rating);
    }
    @Test
    @Rollback
    public void delete_rating_by_id(){
        Rating rating = new Rating(0,4,"Good Course",3);
        Rating rating1 = ratingService.createRating(rating);
        int id = rating1.getId();
        boolean result = ratingService.deleteRatingById(id);
        System.out.println(result);
    }



}
