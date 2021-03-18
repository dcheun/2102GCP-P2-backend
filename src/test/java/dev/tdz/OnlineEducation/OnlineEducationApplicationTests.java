package dev.tdz.OnlineEducation;

import dev.tdz.entities.AppUser;
import dev.tdz.repos.AppUserRepo;
import dev.tdz.services.AppUserService;
import dev.tdz.services.AppUserServiceImpl;
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
	public void create_app_user(){
		AppUser appUser = new AppUser(0,"Bill","Gates","billg@hotmail.com","gates1",2);
		appUserService.createAppUser(appUser);
		System.out.println(appUser);
	}

	@Test
	@Rollback
	void create_user() {
		AppUser user1 = new AppUser(0,"Danny","Cheun","da@gmail.com","tdz1",1);

		this.appUserRepo.save(user1);
		System.out.println(user1);
	}



}
