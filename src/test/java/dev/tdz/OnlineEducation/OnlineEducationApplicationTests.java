package dev.tdz.OnlineEducation;

import dev.tdz.entities.AppUser;
import dev.tdz.entities.Course;
import dev.tdz.repos.AppUserRepo;
import dev.tdz.repos.CourseRepo;
import dev.tdz.services.AppUserService;
import dev.tdz.services.CourseService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@SpringBootTest
@Transactional
class OnlineEducationApplicationTests {

    @Autowired
    AppUserRepo appUserRepo;

    @Autowired
    AppUserService appUserService;

    @Autowired
    CourseRepo courseRepo;

    @Autowired
    CourseService courseService;

    @Test
    @Rollback
    public void create_app_user() {
        AppUser appUser = new AppUser(0, "Bill", "Gates", "b@hotmail.com", "gates1", 2);
        appUserService.createAppUser(appUser);
        System.out.println(appUser);
    }

    @Test
    @Rollback
    void create_user() {
        AppUser user1 = new AppUser(0, "Danny", "Cheun", "da@gmail.com", "tdz1", 1);
        this.appUserRepo.save(user1);
        System.out.println(user1);
    }

    @Test
    @Rollback
    public void get_student_courses(){
        AppUser user = new AppUser(1,"Sam","Araga","sam@gmail.com","sam123",2);
        appUserService.createAppUser(user);
        int id = user.getId();
        Set<Course> course = new HashSet<Course>((Collection<? extends Course>) this.courseRepo.findAll());
        user.setStudentCourses(course);
        System.out.println(course);
        System.out.println(id);
        System.out.println(user);
        System.out.println(user.getStudentCourses());
    }
    @Test
    @Rollback
    public void get_instructor_courses(){
        AppUser user = new AppUser(1,"Joe","Rogue","joe@gmail.com","joe123",1);
        appUserService.createAppUser(user);
        int id = user.getId();
        Set<Course> course = new HashSet<Course>((Collection<? extends Course>) this.courseRepo.findAll());
        user.setInstructorCourses(course);
        System.out.println(course);
        System.out.println(id);
        System.out.println(user);
        System.out.println(user.getInstructorCourses());
    }

    @Test
    @Rollback
    public void get_app_user_without_course(){
        AppUser user = new AppUser(1,"Bill","Murray","bill@gmail.com","bill123",1);
        appUserService.createAppUser(user);
        int id = user.getId();
        System.out.println(id);
        System.out.println(user);
        System.out.println(user.getStudentCourses());
    }

    @Test
    void get_user() {
        AppUser user = this.appUserRepo.findById(4).get();
        System.out.println(user);
        System.out.println(user.getStudentCourses());
        System.out.println();
        AppUser user2 = this.appUserRepo.findById(5).get();
        System.out.println(user2);
        System.out.println(user2.getStudentCourses());
        System.out.println();
        AppUser user3 = this.appUserRepo.findById(20).get();
        System.out.println(user3);
        System.out.println(user3.getStudentCourses());
        System.out.println();
        AppUser user4 = this.appUserRepo.findById(21).get();
        System.out.println(user4);
        System.out.println(user4.getStudentCourses());
        System.out.println();
    }

}
