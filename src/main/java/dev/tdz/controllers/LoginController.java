package dev.tdz.controllers;

import dev.tdz.aspects.Logging;
import dev.tdz.entities.AppUser;
import dev.tdz.exceptions.NotAuthenticatedException;
import dev.tdz.services.AppUserService;
import dev.tdz.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@Component
@RestController
public class LoginController {
    @Autowired
    AppUserService appUserService;

    @Logging
    @PostMapping("/login")
    public String login(@RequestBody AppUser appUser) {
        AppUser authUser;
        try {
            authUser = this.appUserService.authenticate(
                    appUser.getEmail(), appUser.getPassword());
        } catch (NotAuthenticatedException exc) {
            throw new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED, "Failed to login", exc);
        }
        String jwt = JwtUtil.generateJwtToken(authUser.getId(), authUser.getUserRoleId());
        return jwt;
    }
}
