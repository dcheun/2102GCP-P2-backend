package dev.tdz.controllers;

import com.auth0.jwt.interfaces.DecodedJWT;
import dev.tdz.aspects.Authorized;
import dev.tdz.entities.AppUser;
import dev.tdz.exceptions.NotFoundException;
import dev.tdz.services.AppUserService;
import dev.tdz.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;
import java.util.Set;

@Component
@RestController
public class AppUserController {

    @Autowired
    AppUserService appUserService;

    @Authorized
    @GetMapping("/users")
    public Set<AppUser> retrieveAllUsers() {
        Set<AppUser> users = this.appUserService.getAllAppUsers();
        return users;
    }

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

    @PostMapping("/users")
    public AppUser createUser(@RequestBody AppUser user) {
        this.appUserService.createAppUser(user);
        return user;
    }

    @PutMapping("/users/{id}")
    public AppUser updateUser(@PathVariable int id, @RequestBody AppUser user) {
        user.setId(id);
        this.appUserService.updateAppUser(user);
        return user;
    }

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
