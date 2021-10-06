package com.BookstoreLearning.bookstore.security.dao;

import com.BookstoreLearning.bookstore.security.entity.AppUser;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service("fakeUserDao")
public class UserRepo implements AppUserDao {
    /**
     * HashMap of Users gives us O(1) look up times
     * and O(n) worst case search. The list does not need
     * to be sorted in any particular order so hashmap is useful here
     * Memory cost may need to be considered vs faster look up.
     */
    private static Map<String, AppUser> userDB = new HashMap<>();

    /**
     * Gets User from userDB using username
     * Username is the hashmap key so look up
     * is O(1)
     *
     * @param userName
     * @return
     */
    @Override
    public UserDetails getAppUserByUsername(String userName) {
        return userDB.get(userName);
    }

    /**
     * Gets users using the ID and using a stream to filter the HashMap
     *
     * @param id
     * @return
     */
    @Override
    public AppUser getAppUserById(long id) {
        return userDB.entrySet()
                .parallelStream()
                .filter(u -> id == u.getValue().getId())
                .map(Map.Entry::getValue)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("User with id: " + id + " does not exist"));
    }

    /**
     * Gets an app user by Email using stream
     *
     * @param email
     * @return
     */
    @Override
    public AppUser getAppUserByEmail(String email) {
        return userDB.entrySet()
                .parallelStream()
                .filter(u -> email.equals(u.getValue().getEmail()))
                .map(Map.Entry::getValue)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("User with id: " + email + " does not exist"));
    }

    /**
     * Get user by name
     *
     * @param name
     * @return
     */
    @Override
    public AppUser getAppUserByName(String name) {
        return userDB.entrySet()
                .parallelStream()
                .filter(u -> name.equals(u.getValue().getName()))
                .map(Map.Entry::getValue)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("User with name: " + name + " does not exist"));
    }

    /**
     * Create a single new user
     *
     * @param user
     */
    @Override
    public void createAppUser(AppUser user) {
        userDB.put(user.getName(), user);
    }

    /**
     * Create a list of users
     *
     * @param users
     */
    @Override
    public void createAppUsersList(AppUser[] users) {
        for (AppUser user : users) {
            userDB.put(user.getUsername(), user);
        }
//        userDB.forEach((key, value) -> System.out.println("Key: " + key + " " + " value: " + value.getUsername()));
    }

    @Override
    public Map<String, AppUser> getAllUsers() {
        return userDB;
    }
}
