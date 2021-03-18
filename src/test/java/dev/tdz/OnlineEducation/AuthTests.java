package dev.tdz.OnlineEducation;

import dev.tdz.entities.AppUser;
import dev.tdz.repos.AppUserRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AuthTests {
    @Autowired
    AppUserRepo appUserRepo;

    @Test
    void authSuccess() {
        AppUser appUser = this.appUserRepo.authenticate("hpotter@hogwarts.edu", "potter123");
        System.out.println(appUser);
    }

    @Test
    void authFail() {
        AppUser appUser = this.appUserRepo.authenticate("hpotter@hogwarts.edu", "wrongPassword");
        System.out.println(appUser);
    }
}
