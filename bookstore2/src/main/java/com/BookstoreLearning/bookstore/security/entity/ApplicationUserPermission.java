package com.BookstoreLearning.bookstore.security.entity;

import org.springframework.security.jackson2.SimpleGrantedAuthorityMixin;

public enum ApplicationUserPermission {
    BOOK_READ,
    BOOK_WRITE,
    USER_READ,
    USER_WRITE;
}
