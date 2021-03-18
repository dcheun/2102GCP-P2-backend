package dev.tdz.services;

import dev.tdz.entities.AppUser;
import dev.tdz.exceptions.NotAuthenticatedException;
import dev.tdz.exceptions.NotFoundException;
import dev.tdz.repos.AppUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;

@Component
@Service
public class AppUserServiceImpl implements AppUserService{

    @Autowired
    AppUserRepo appUserRepo;

    @Override
    public AppUser createAppUser(AppUser appUser) {

         this.appUserRepo.save(appUser);
         return appUser;
    }

    @Override
    public AppUser getAppUserById(int id) {
        AppUser appUser;
        try {
            appUser = this.appUserRepo.findById(id).get();
            appUser.setPassword(null);
        } catch (NoSuchElementException e) {
            throw new NotFoundException("No such user exists");
        }
        return appUser;
    }

    @Override
    public Set<AppUser> getAllAppUsers() {
        Set<AppUser> appUsers = new HashSet<AppUser>((Collection<? extends AppUser>) this.appUserRepo.findAll());
        for (AppUser user : appUsers) {
            user.setPassword(null);
        }
        return appUsers;
    }

    @Override
    public AppUser authenticate(String email, String pw) {
        AppUser authUser = this.appUserRepo.authenticate(email, pw);
        if (authUser == null) {
            throw new NotAuthenticatedException("Failed to authenticate user");
        }
        return authUser;
    }

    @Override
    public AppUser updateAppUser(AppUser appUser) {

        return this.appUserRepo.save(appUser);
    }

    @Override
    public boolean deleteAppUserById(int id) {

        try {
            this.appUserRepo.deleteById(this.getAppUserById(id).getId());
//            this.getAppUserById(id);
//                return false;
            return true;
        } catch(NotFoundException e){
            // log the error
            throw e;
        } catch (Exception e) {
            return false;
        }
    }


}
