package com.BookstoreLearning.bookstore.security.controller;

import com.BookstoreLearning.bookstore.security.entity.AppUser;
import com.BookstoreLearning.bookstore.security.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Controller class for creating and retriving users
 */
@RequestMapping("api/admin/users")
@RestController
public class UserController {
    private final UserService userService;

    // Constructor
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // GET request: get user by ID
    @GetMapping(path = "getID/{id}")
    @PostAuthorize("hasAuthority('USER_READ')")
    public UserDetails getAppUserById(@PathVariable("id") long id) {
        return this.userService.getAppUserById(id);
    }

    // GET request: get user by Email
    @GetMapping(path = "getEmail/{email}")
    @PostAuthorize("hasAuthority('USER_READ')")
    public UserDetails getAppUserByEmail(@PathVariable("email") String email) {
        return this.userService.getAppUserByEmail(email);
    }

    // GET request: get user by name
    @GetMapping(path = "getName/{name}")
    @PostAuthorize("hasAuthority('USER_READ')")
    public UserDetails getAppUserByName(@PathVariable("name") String name) {
        return this.userService.getAppUserByName(name);
    }

    // POST request: create a new user
    @PostMapping(path = "create/user")
    @PreAuthorize("hasAuthority('USER_WRITE')")
    public void createAppUser(@RequestBody AppUser user) {
        this.userService.createAppUser(user);
    }

    // POST request: create a users using array of Users
    @PostMapping(path = "create/users")
    @PreAuthorize("hasAuthority('USER_WRITE')")
    public void createAppUsersList(@RequestBody AppUser[] users) {
        this.userService.createAppUsersList(users);
    }

    // GET request: get user by username
    @GetMapping(path = "getUsername/{username}")
    @PostAuthorize("hasAuthority('USER_READ')")
    public UserDetails loadUserByUsername(@PathVariable("username") String username) throws UsernameNotFoundException {
        return this.userService.loadUserByUsername(username);
    }

    // GET request: get all users
    @GetMapping(path = "getAllUsers")
    @PostAuthorize("hasAuthority('USER_READ')")
    public Map<String, AppUser> getAllUsers() {
        return this.userService.getAllUsers();
    }
}
