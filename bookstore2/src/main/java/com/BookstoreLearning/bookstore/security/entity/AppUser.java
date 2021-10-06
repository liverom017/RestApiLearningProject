package com.BookstoreLearning.bookstore.security.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collection;

/**
 * Class for a specific bookstore user
 * A user has one or more roles
 */

public class AppUser implements UserDetails {
    // Attributes
    private final long id;
    private final String name;
    private final String username;
    private final String password;
    private final String email;
    private final UserRole userRole;
    private final boolean isAccountNonExpired;
    private final boolean isAccountNonLocked;
    private final boolean isCredentialsNonExpired;
    private final boolean isEnabled;

    // Constructor
    public AppUser(@JsonProperty("id") long id,
                   @JsonProperty("name") String name,
                   @JsonProperty("username") String username,
                   @JsonProperty("email") String email,
                   @JsonProperty("password") String password,
                   @JsonProperty("userRole") String userRole,
                   @JsonProperty("isAccountNonExpired") boolean isAccountNonExpired,
                   @JsonProperty("isAccountNonLocked") boolean isAccountNonLocked,
                   @JsonProperty("isCredentialsNonExpired") boolean isCredentialsNonExpired,
                   @JsonProperty("isEnabled") boolean isEnabled) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.email = email;
        this.password = passwordEncoder().encode(password);
        this.userRole = UserRole.valueOf(userRole);
        this.isAccountNonExpired = isAccountNonExpired;
        this.isAccountNonLocked = isAccountNonLocked;
        this.isCredentialsNonExpired = isCredentialsNonExpired;
        this.isEnabled = isEnabled;

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }


    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return userRole.getGrantedAuthorities();
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return isAccountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isCredentialsNonExpired;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isAccountNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }
}