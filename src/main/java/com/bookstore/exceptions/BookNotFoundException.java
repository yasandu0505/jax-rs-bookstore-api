package com.bookstore.exceptions;

// BookNotFoundException.java
public class BookNotFoundException extends RuntimeException {
    public BookNotFoundException(String message) {
        super(message);
    }
}