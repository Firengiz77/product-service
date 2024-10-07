package com.example.product_service.exception;

public class ImageNotFoundException extends RuntimeException {
    private static final String MESSAGE = "Image not found";

    public ImageNotFoundException() {
        super(MESSAGE);
    }
}
