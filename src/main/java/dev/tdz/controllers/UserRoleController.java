package dev.tdz.controllers;

import dev.tdz.aspects.Logging;
import dev.tdz.entities.UserRole;
import dev.tdz.services.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@Component
@RestController
public class UserRoleController {
    @Autowired
    UserRoleService userRoleService;

    @Logging
    @GetMapping("/userroles")
    public Set<UserRole> retrieveAllUserRoles() {
        Set<UserRole> userRoles = this.userRoleService.getAllUserRoles();
        return userRoles;
    }
}
