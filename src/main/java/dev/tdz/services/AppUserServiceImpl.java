package dev.tdz.services;

import dev.tdz.aspects.ServiceLogging;
import dev.tdz.entities.AppUser;
import dev.tdz.exceptions.NotAuthenticatedException;
import dev.tdz.exceptions.NotFoundException;
import dev.tdz.repos.AppUserRepo;
import dev.tdz.utils.AppUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;

@Component
@Service
public class AppUserServiceImpl implements AppUserService {

    private static final Logger logger = Logger.getLogger(
            AppUserServiceImpl.class.getName());

    @Autowired
    AppUserRepo appUserRepo;

    @ServiceLogging
    @Override
    public AppUser createAppUser(AppUser appUser) {
        try {
            return this.appUserRepo.save(appUser);
        } catch (Exception e) {
            AppUtil.logException(logger, e, "createAppUser: Unable to create");
            throw e;
        }
    }

    @ServiceLogging
    @Override
    public AppUser getAppUserById(int id) {
        try {
            // NoSuchElementException is thrown if no record was found.
            return this.appUserRepo.findById(id).get();
        } catch (NoSuchElementException e) {
            throw new NotFoundException("No such user exists");
        } catch (Exception e) {
            AppUtil.logException(logger, e,
                    "getAppUserById: Unable to get record with id=" + id);
            throw e;
        }
    }

    @ServiceLogging
    @Override
    public Set<AppUser> getAllAppUsers() {
        try {
            return new HashSet<>(
                    (Collection<? extends AppUser>) this.appUserRepo.findAll());
        } catch (Exception e) {
            AppUtil.logException(logger, e, "getAllAppUsers: Unable to retrieve");
            throw e;
        }
    }

    @ServiceLogging
    @Override
    public AppUser getAppUserByEmail(String email) {
        Set<AppUser> allAppUsers = this.getAllAppUsers();
        for (AppUser u : allAppUsers) {
            if (u.getEmail().equals(email)) {
                return u;
            }
        }
        throw new NotFoundException("No user with that email exists");
    }

    @ServiceLogging
    @Override
    public AppUser authenticate(AppUser appUser) {
        try {
            AppUser authUser = this.appUserRepo.authenticate(
                    appUser.getEmail(), appUser.getPassword());
            // DB will return user object if password matches, otherwise null.
            if (authUser == null) {
                throw new NotAuthenticatedException("Failed to authenticate user");
            }
            return authUser;
        } catch (NotAuthenticatedException e) {
            // NOTE: Logging performed in logging aspect.
            throw e;
        } catch (Exception e) {
            // NOTE: These are mainly to log unforeseen errors.
            AppUtil.logException(logger, e,
                    "authenticate: Unable to authenticate user");
            throw e;
        }
    }

    @ServiceLogging
    @Override
    public AppUser updateAppUser(AppUser appUser) {
        try {
            return this.appUserRepo.save(appUser);
        } catch (Exception e) {
            AppUtil.logException(logger, e, "updateAppUser: Unable to update");
            throw e;
        }
    }

    @ServiceLogging
    @Override
    public boolean deleteAppUserById(int id) {
        try {
            // NOTE: Checking existence of entity to be deleted is done at
            // controller layer.
            this.appUserRepo.deleteById(id);
            return true;
        } catch (Exception e) {
            AppUtil.logException(logger, e,
                    "deleteAppUserById: Unable to delete user with id=" + id);
            throw e;
        }
    }

}
