package com.BookstoreLearning.bookstore.security.services;

import com.BookstoreLearning.bookstore.security.dao.AppUserDao;
import com.BookstoreLearning.bookstore.security.entity.AppUser;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class UserService implements UserDetailsService {
    private final AppUserDao appUserDao;

    public UserService(@Qualifier("fakeUserDao") AppUserDao appUserDao) {
        this.appUserDao = appUserDao;
    }

    public UserDetails getAppUserById(long id){
        return this.appUserDao.getAppUserById(id);
    }

    public UserDetails getAppUserByEmail(String email){
        return this.appUserDao.getAppUserByEmail(email);
    }

    public UserDetails getAppUserByName(String name){
        return this.appUserDao.getAppUserByName(name);
    }

    public void createAppUser(AppUser user){
        this.appUserDao.createAppUser(user);
    }

    public void createAppUsersList(AppUser[] users){
        this.appUserDao.createAppUsersList(users);
    }

    public Map<String, AppUser> getAllUsers(){
        return this.appUserDao.getAllUsers();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.appUserDao.getAppUserByUsername(username);
    }
}
