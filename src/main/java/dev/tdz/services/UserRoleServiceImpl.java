package dev.tdz.services;

import dev.tdz.aspects.ServiceLogging;
import dev.tdz.entities.UserRole;
import dev.tdz.exceptions.NotFoundException;
import dev.tdz.repos.UserRoleRepo;
import dev.tdz.utils.AppUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;

@Component
@Service
public class UserRoleServiceImpl implements UserRoleService {

    private static final Logger logger = Logger.getLogger(
            UserRoleServiceImpl.class.getName());

    @Autowired
    UserRoleRepo userRoleRepo;

    @ServiceLogging
    @Override
    public UserRole createUserRole(UserRole userRole) {
        try {
            return this.userRoleRepo.save(userRole);
        } catch (Exception e) {
            AppUtil.logException(logger, e, "createRole: Unable to create");
            throw e;
        }
    }

    @ServiceLogging
    @Override
    public UserRole getUserRoleById(int id) {
        try {
            return this.userRoleRepo.findById(id).get();
        } catch (NoSuchElementException e) {
            throw new NotFoundException("No such role exists");
        } catch (Exception e) {
            AppUtil.logException(logger, e,
                    "getUserRoleById: Unable to get record with id=" + id);
            throw e;
        }
    }

    @ServiceLogging
    @Override
    public Set<UserRole> getAllUserRoles() {
        try {
            return new HashSet<>(
                    (Collection<? extends UserRole>) this.userRoleRepo.findAll());
        } catch (Exception e) {
            AppUtil.logException(logger, e, "getAllUserRoles: Unable to retrieve");
            throw e;
        }
    }

    @ServiceLogging
    @Override
    public UserRole updateUserRole(UserRole userRole) {
        try {
            return this.userRoleRepo.save(userRole);
        } catch (Exception e) {
            AppUtil.logException(logger, e, "updateUserRole: Unable to update");
            throw e;
        }
    }

    @ServiceLogging
    @Override
    public boolean deleteUserRoleById(int id) {
        try {
            this.userRoleRepo.deleteById(id);
            return true;
        } catch (EmptyResultDataAccessException e) {
            throw new NotFoundException("No such record exists");
        } catch (Exception e) {
            AppUtil.logException(logger, e,
                    "deleteUserRoleById: Unable to delete role with id=" + id);
            throw e;
        }
    }

}
