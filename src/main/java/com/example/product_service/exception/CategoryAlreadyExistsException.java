package com.example.product_service.exception;

public class CategoryAlreadyExistsException extends RuntimeException{

    private static final String MESSAGE = "Category already exists";

    public CategoryAlreadyExistsException() {
        super(MESSAGE);
    }
}
