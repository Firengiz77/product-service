package com.example.product_service.exception;

public class ProductAlreadyExistsException extends RuntimeException{
    private static final String MESSAGE = "Product already exists";

    public ProductAlreadyExistsException() {
        super(MESSAGE);
    }

}
