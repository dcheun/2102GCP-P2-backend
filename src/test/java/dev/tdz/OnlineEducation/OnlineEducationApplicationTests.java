package dev.tdz.OnlineEducation;

import dev.tdz.entities.AppUser;
import dev.tdz.repos.AppUserRepo;
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

	@Test
	@Rollback
	void create_user() {
		AppUser user1 = new AppUser(0,"Danny","Cheun","da@gmail.com","tdz1",1);

		this.appUserRepo.save(user1);
		System.out.println(user1);
	}

}
