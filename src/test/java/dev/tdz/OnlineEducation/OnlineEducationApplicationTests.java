package dev.tdz.OnlineEducation;

import dev.tdz.entities.AppUser;
import dev.tdz.repos.AppUserRepo;
import dev.tdz.services.AppUserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;

@SpringBootTest
@Transactional
class OnlineEducationApplicationTests {

    @Autowired
    AppUserRepo appUserRepo;

    @Autowired
    AppUserService appUserService;

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
