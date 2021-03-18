package dev.tdz.services;


import dev.tdz.entities.AppUser;

import java.util.Set;

public interface AppUserService {

    AppUser createAppUser(AppUser appUser);

    AppUser getAppUserById(int id);
    Set<AppUser> getAllAppUsers();

    AppUser updateAppUser(AppUser appUser);

    boolean deleteAppUserById(int id);

}
