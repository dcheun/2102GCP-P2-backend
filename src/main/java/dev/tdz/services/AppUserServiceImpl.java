package dev.tdz.services;

import dev.tdz.entities.AppUser;
import dev.tdz.repos.AppUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

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
        AppUser appUser = this.appUserRepo.findById(id).get();
        return appUser;
    }

    @Override
    public Set<AppUser> getAllAppUsers() {
        Set<AppUser> appUsers = new HashSet<AppUser>((Collection<? extends AppUser>) this.appUserRepo.findAll());
        return appUsers;
    }

    @Override
    public AppUser updateAppUser(AppUser appUser) {

        return this.appUserRepo.save(appUser);
    }

    @Override
    public boolean deleteAppUserById(int id) {

        this.appUserRepo.deleteById(id);
        try {
            this.getAppUserById(id);
                return false;
        } catch(NoSuchElementException e){
            return true;
        }

    }
}
