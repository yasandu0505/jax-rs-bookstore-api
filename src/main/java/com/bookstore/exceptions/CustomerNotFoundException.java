package com.bookstore.exceptions;

// CustomerNotFoundException.java
public class CustomerNotFoundException extends RuntimeException {
    public CustomerNotFoundException(String message) {
        super(message);
    }
}
