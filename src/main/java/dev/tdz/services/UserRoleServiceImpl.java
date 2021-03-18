package dev.tdz.services;

import dev.tdz.entities.UserRole;
import dev.tdz.repos.UserRoleRepo;
import net.bytebuddy.implementation.auxiliary.AuxiliaryType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;


@Component
@Service
public class UserRoleServiceImpl implements UserRoleService{

    @Autowired
    UserRoleRepo userRoleRepo;

    @Override
    public UserRole createUserRole(UserRole userRole) {
        return null;
    }

    @Override
    public UserRole getUserRoleById(int id) {
        return null;
    }

    @Override
    public Set<UserRole> getAllUserRoles() {
        Set<UserRole> userRoles = new HashSet<UserRole>((Collection<? extends UserRole>) this.userRoleRepo.findAll());
        return userRoles;
    }

    @Override
    public UserRole updateUserRole(UserRole userRole) {
        return null;
    }

    @Override
    public boolean deleteUserRoleById(int id) {
        return false;
    }
}
