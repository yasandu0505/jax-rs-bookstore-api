package com.bookstore.storage;

import com.bookstore.model.*;
import java.util.*;

public class DataStore {

    // Maps for storing the data
    public static Map<Integer, Book> books = new HashMap<>();
    public static Map<Integer, Author> authors = new HashMap<>();
    public static Map<Integer, Customer> customers = new HashMap<>();
    public static Map<Integer, List<CartItem>> carts = new HashMap<>();
    public static Map<Integer, List<Order>> orders = new HashMap<>();

    // Static counters for generating unique IDs
    private static int authorIdCounter = 1;
    private static int customerIdCounter = 1;
    private static int orderIdCounter = 1;
    private static int bookIdCounter = 1;

    // Methods to generate unique IDs for Author, Customer, and Order
    public static int generateAuthorId() {
        return authorIdCounter++;
    }

    public static int generateCustomerId() {
        return customerIdCounter++;
    }

    public static int generateOrderId() {
        return orderIdCounter++;
    }

    public static int generateBookId() {
        return bookIdCounter++;
    }

    // You may want to add a method to reset the ID counters (optional, for testing)
    public static void resetCounters() {
        authorIdCounter = 1;
        customerIdCounter = 1;
        orderIdCounter = 1;
        bookIdCounter = 1;
    }
}
