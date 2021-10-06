package com.BookstoreLearning.bookstore.security.entity;

import com.google.common.collect.Sets;
import com.BookstoreLearning.bookstore.security.entity.ApplicationUserPermission.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static com.BookstoreLearning.bookstore.security.entity.ApplicationUserPermission.*;

// Types of roles a user can have
public enum UserRole {
    USER(Sets.newHashSet(BOOK_READ)),
    AUTHOR(Sets.newHashSet(BOOK_READ,BOOK_WRITE)),
    ADMIN(Sets.newHashSet(BOOK_READ,BOOK_WRITE,USER_READ, USER_WRITE));

    private final Set<ApplicationUserPermission> userPermissions;

    UserRole(Set<ApplicationUserPermission> userPermissions) {
        this.userPermissions = userPermissions;
    }

    public Set<ApplicationUserPermission> getPermissions() {
        return userPermissions;
    }

    public Set<SimpleGrantedAuthority> getGrantedAuthorities() {
        Set<SimpleGrantedAuthority> grantedAuthorities = getPermissions().stream()
                .map(userPermissions -> new SimpleGrantedAuthority(userPermissions.name()))
                        .collect(Collectors.toSet());
        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return grantedAuthorities;
    }
}
