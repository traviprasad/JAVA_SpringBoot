package com.dev.spring.restservice.user;

public class UsertNotFoundException extends RuntimeException {

    public UsertNotFoundException(String message) {
        super(message);
    }
}
