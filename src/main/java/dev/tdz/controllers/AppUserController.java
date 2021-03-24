package dev.tdz.controllers;

import dev.tdz.aspects.Authorized;
import dev.tdz.aspects.Logging;
import dev.tdz.entities.AppUser;
import dev.tdz.exceptions.NotFoundException;
import dev.tdz.services.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Set;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@Component
@RestController
public class AppUserController {

    @Autowired
    AppUserService appUserService;

    @Logging
    @GetMapping("/users")
    public Set<AppUser> retrieveAllUsers() {
        return this.appUserService.getAllAppUsers();
    }

    @Logging
    @GetMapping("/users/{id}")
    public AppUser getUserById(@PathVariable int id) {
        AppUser user;
        try {
            user = this.appUserService.getAppUserById(id);
        } catch (NotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
        return user;
    }

    @Logging
    @Authorized
    @PostMapping("/users")
    public AppUser createUser(@RequestBody AppUser user) {
        this.appUserService.createAppUser(user);
        return user;
    }

    @Logging
    @Authorized
    @PutMapping("/users/{id}")
    public AppUser updateUser(@PathVariable int id, @RequestBody AppUser user) {
        user.setId(id);
        this.appUserService.updateAppUser(user);
        return user;
    }

    @Logging
    @Authorized
    @DeleteMapping("/users/{id}")
    public Boolean deleteUserById(@PathVariable int id) {
        Boolean result;
        try {
            result = this.appUserService.deleteAppUserById(id);
        } catch (NotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
        return result;
    }
}
