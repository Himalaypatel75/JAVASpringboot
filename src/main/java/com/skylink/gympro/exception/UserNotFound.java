package com.skylink.gympro.exception;

public class UserNotFound extends RuntimeException {

    public UserNotFound(String message) {
        super(message);
    }
}
