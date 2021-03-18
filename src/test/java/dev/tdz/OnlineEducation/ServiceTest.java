package dev.tdz.OnlineEducation;


import dev.tdz.entities.AppUser;
import dev.tdz.entities.Course;
import dev.tdz.entities.CourseMaterial;
import dev.tdz.entities.Rating;
import dev.tdz.services.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
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
    public void create_app_user(){
        AppUser appUser = new AppUser(0,"Bill","Gates","big@hotmail.com","gates1",2);
        appUserService.createAppUser(appUser);
        System.out.println(appUser);
    }

    @Test
    public void create_course_material(){
        CourseMaterial courseMaterial = new CourseMaterial(0,"video","Intro to managing your finance",1);
        courseMaterialService.createCourseMaterial(courseMaterial);
        System.out.println(courseMaterial);
    }

    @Test
    public void create_course(){
        Course course = new Course(0,"Java course", "Learn full stack java", 6);
        courseService.createCourse(course);
        System.out.println(course);
    }

    @Test
    public void create_rating(){
        Rating rating = new Rating(0,4,"Good Course",3);
        ratingService.createRating(rating);
        System.out.println(rating);
    }

    @Test
    public void delete_app_user_by_id(){
        AppUser appUser = new AppUser(0,"John", "Smith","js.hotmail.com","jsss123",1);
        AppUser appUser1 = appUserService.createAppUser(appUser);
        int id = appUser1.getId();
        boolean result = appUserService.deleteAppUserById(id);
        System.out.println(result);

    }

}
