package com.example.product_service.exception;

public class NoAccessException extends RuntimeException{
    private final static String MESSAGE = "You Don't have permission to access this resource";

    public NoAccessException() {
        super(MESSAGE);
    }
}
