package com.BookstoreLearning.bookstore.security;

import com.BookstoreLearning.bookstore.security.JWT.JWTAuthenticationFilter;
import com.BookstoreLearning.bookstore.security.JWT.JwtTokenVerifier;
import com.BookstoreLearning.bookstore.security.entity.AppUser;
import com.BookstoreLearning.bookstore.security.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class AppSecurityConfig extends WebSecurityConfigurerAdapter {
    /**
     * Security Configuration to define what paths are public and which require authentication
     */
    private final UserService userService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }

    @Autowired
    public AppSecurityConfig(UserService userService) {
        this.userService = userService;
    }

    // Hard coded initial users
    private AppUser[] initialAccounts() {
        return new AppUser[]{
                new AppUser(
                        1,
                        "admin",
                        "Admin",
                        "admin@gmail.com",
                        "password",
                        "ADMIN",
                        true,
                        true,
                        true,
                        true
                ),
                new AppUser(
                        2,
                        "user",
                        "User",
                        "user@gmail.com",
                        "password",
                        "USER",
                        true,
                        true,
                        true,
                        true
                ),
                new AppUser(
                        3,
                        "John",
                        "Author",
                        "author@gmail.com",
                        "password",
                        "AUTHOR",
                        true,
                        true,
                        true,
                        true
                )
        };
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // Hard coded initial users
        AppUser[] initialAccounts = initialAccounts();
        // Add some initial users to our database
        userService.createAppUsersList(initialAccounts);

        auth.authenticationProvider(daoAuthenticationProvider());

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // Enable CORS and disable CSRF
        http
                .cors().and()
                .csrf().disable();
        http
                .formLogin();
        // Set session management to stateless
        http
                .sessionManagement().sessionCreationPolicy(STATELESS).and();

        // JWT Authentication and Verification filters
        http
                .addFilter(new JWTAuthenticationFilter(authenticationManager()))
                .addFilterAfter(new JwtTokenVerifier(), JWTAuthenticationFilter.class);
        // Endpoints and permissions are handled in the controller

    }


    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider =
                new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder());
        provider.setUserDetailsService(userService);
        return provider;
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }
}

