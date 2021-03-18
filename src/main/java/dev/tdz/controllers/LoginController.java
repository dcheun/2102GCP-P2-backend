package dev.tdz.controllers;

import dev.tdz.entities.AppUser;
import dev.tdz.exceptions.NotAuthenticatedException;
import dev.tdz.services.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;

import java.nio.file.AccessDeniedException;

@Component
@RestController
public class LoginController {
    @Autowired
    AppUserService appUserService;

    @PostMapping("/login")
    public AppUser login(@RequestBody AppUser appUser) {
        AppUser authUser;
        try {
            authUser = this.appUserService.authenticate(appUser.getEmail(), appUser.getPassword());
        } catch (NotAuthenticatedException exc) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Failed to login", exc);
        }
        authUser.setPassword(null);
        return authUser;
    }

}
