package com.example.product_service.exception;

public class CategoryNotFoundException extends RuntimeException{

    private static final String MESSAGE = "Category not found";

    public CategoryNotFoundException() {
        super(MESSAGE);
    }

}
