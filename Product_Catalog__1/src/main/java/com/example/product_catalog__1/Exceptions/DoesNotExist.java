package com.example.product_catalog__1.Exceptions;

public class DoesNotExist extends RuntimeException {
    public DoesNotExist(String message) {
        super(message);
    }
}
