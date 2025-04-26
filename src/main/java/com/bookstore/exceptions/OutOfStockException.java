package com.bookstore.exceptions;

// OutOfStockException.java
public class OutOfStockException extends RuntimeException {
    public OutOfStockException(String message) {
        super(message);
    }
}
