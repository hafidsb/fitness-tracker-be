package com.hafidsb.fitnesstracker.authservice.exception;

public class AuthException extends RuntimeException {
    public AuthException(String message) {
        super(message);
    }

    public AuthException() {
        super("Invalid email or password");
    }
}
