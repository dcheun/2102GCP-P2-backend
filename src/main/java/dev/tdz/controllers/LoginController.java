package dev.tdz.controllers;

import dev.tdz.aspects.Authentication;
import dev.tdz.aspects.ErrorHandler;
import dev.tdz.aspects.Logging;
import dev.tdz.entities.AppUser;
import dev.tdz.services.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@Component
@RestController
public class LoginController {

    @Autowired
    AppUserService appUserService;

    @Logging
    @Authentication
    @ErrorHandler
    @PostMapping("/login")
    public String login(@RequestBody AppUser appUser) {
        HttpServletRequest request = (
                (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()
        ).getRequest();

        this.appUserService.authenticate(appUser);
        return (String) request.getAttribute("encodedJWT");
    }

}
