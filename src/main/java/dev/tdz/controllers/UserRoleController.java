package dev.tdz.controllers;

import dev.tdz.entities.UserRole;
import dev.tdz.services.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@Component
@RestController
public class UserRoleController {
    @Autowired
    UserRoleService userRoleService;

    @GetMapping("/userroles")
    public Set<UserRole> retrieveAllUserRoles() {
        Set<UserRole> userRoles = this.userRoleService.getAllUserRoles();
        return userRoles;
    }
}
