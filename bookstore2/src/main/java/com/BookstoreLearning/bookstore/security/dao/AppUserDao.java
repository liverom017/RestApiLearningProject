package com.BookstoreLearning.bookstore.security.dao;

import com.BookstoreLearning.bookstore.security.entity.AppUser;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Map;

public interface AppUserDao {
    // Get user by username
    UserDetails getAppUserByUsername(String userName);

    // Get user by ID
    UserDetails getAppUserById(long id);

    // Get user by email
    UserDetails getAppUserByEmail(String email);

    // Get user by name
    UserDetails getAppUserByName(String name);

    // Get all Users
    Map<String, AppUser> getAllUsers();

    //create new User
    void createAppUser(AppUser user);

    // Create entire list of User
    void createAppUsersList(AppUser[] users);

}
