package dev.tdz.controllers;

import dev.tdz.aspects.Authorization;
import dev.tdz.aspects.ErrorHandler;
import dev.tdz.aspects.Logging;
import dev.tdz.entities.AppUser;
import dev.tdz.exceptions.NotAuthorizedException;
import dev.tdz.services.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Set;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@Component
@RestController
public class AppUserController {

    @Autowired
    AppUserService appUserService;

    @Logging
    @ErrorHandler
    @GetMapping("/users")
    public Set<AppUser> retrieveAllUsers() {
        Set<AppUser> users = this.appUserService.getAllAppUsers();
        // Remove password before sending back response.
        for (AppUser user : users) {
            user.setPassword(null);
        }
        return users;
    }

    @Logging
    @ErrorHandler
    @GetMapping("/users/{id}")
    public AppUser getUserById(@PathVariable int id) {
        AppUser user = this.appUserService.getAppUserById(id);
        // Remove password before sending back response.
        user.setPassword(null);
        return user;
    }

    @Logging
    @ErrorHandler
    @PostMapping("/users")
    public AppUser createUser(@RequestBody AppUser user) {
        // Override fields of Transient object.
        user.setId(0);
        this.appUserService.createAppUser(user);
        user.setPassword(null);
        return user;
    }

    @Logging
    @Authorization
    @ErrorHandler
    @PutMapping("/users/{id}")
    public AppUser updateUser(@PathVariable int id, @RequestBody AppUser user) {
        // Check existence of entity to be updated.
        AppUser currUser = this.appUserService.getAppUserById(id);
        // Get JWT claims.
        HttpServletRequest request = (
                (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()
        ).getRequest();
        Integer authUserId = (Integer) request.getAttribute("authUserId");
        // Check if user is updating himself/herself.
        if (authUserId != id) {
            throw new NotAuthorizedException("Access denied");
        }
        // Override fields of Transient object.
        user.setId(id);
        // We need to be able to send null as password from the frontend.
        // This means we do not want to change the pw.
        // Setting this to the current pw will prevent Spring/Hibernate
        // from updating this field due to the @DynamicUpdate annotation.
        if (user.getPassword() == null) {
            user.setPassword(currUser.getPassword());
        }
        this.appUserService.updateAppUser(user);
        // Remove password before sending response back.
        user.setPassword(null);
        return user;
    }

    @Logging
    @Authorization
    @ErrorHandler
    @DeleteMapping("/users/{id}")
    public Boolean deleteUserById(@PathVariable int id) {
        // Get JWT claims.
        HttpServletRequest request = (
                (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()
        ).getRequest();
        Integer authUserId = (Integer) request.getAttribute("authUserId");
        // Check if user is deleting themselves.
        if (authUserId != id) {
            throw new NotAuthorizedException("Access denied");
        }
        return this.appUserService.deleteAppUserById(id);
    }

}
