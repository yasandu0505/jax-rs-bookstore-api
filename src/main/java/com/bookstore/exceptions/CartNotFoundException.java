package com.bookstore.exceptions;

// CartNotFoundException.java
public class CartNotFoundException extends RuntimeException {
    public CartNotFoundException(String message) {
        super(message);
    }
}