package com.example.product_service.exception;

public class NoAuthenticationException extends RuntimeException {
    private final static String MESSAGE = "You are not logged in";

    public NoAuthenticationException() {
        super(MESSAGE);
    }
}
