package dev.tdz.services;

import dev.tdz.entities.UserRole;

import java.util.Set;

public interface UserRoleService {

    UserRole createUserRole(UserRole userRole);

    UserRole getUserRoleById(int id);

    Set<UserRole> getAllUserRoles();

    UserRole updateUserRole(UserRole userRole);

    boolean deleteUserRoleById(int id);

}
