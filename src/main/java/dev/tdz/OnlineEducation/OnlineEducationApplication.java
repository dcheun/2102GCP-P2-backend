package dev.tdz.OnlineEducation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Component;

@ComponentScan(basePackages = {"dev.tdz"})
@ComponentScan(basePackages = {"dev.tdz.services"})
@EnableJpaRepositories(basePackages = {"dev.tdz.repos"})
@EntityScan(basePackages = {"dev.tdz.entities"})
@SpringBootApplication
public class OnlineEducationApplication {

	public static void main(String[] args) {
		SpringApplication.run(OnlineEducationApplication.class, args);
	}

}
