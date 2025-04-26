package com.bookstore.exceptions;


// AuthorNotFoundException.java
public class AuthorNotFoundException extends RuntimeException {
    public AuthorNotFoundException(String message) {
        super(message);
    }
}