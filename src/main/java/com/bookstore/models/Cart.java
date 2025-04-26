package com.bookstore.models;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private int customerId;
    private List<CartItem> items;
    
    // Default constructor required for JAX-RS
    public Cart() {
        this.items = new ArrayList<>();
    }
    
    public Cart(int customerId) {
        this.customerId = customerId;
        this.items = new ArrayList<>();
    }
    
    // Getters and Setters
    public int getCustomerId() {
        return customerId;
    }
    
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }
    
    public List<CartItem> getItems() {
        return items;
    }
    
    public void setItems(List<CartItem> items) {
        this.items = items;
    }
    
    // Helper methods
    public void addItem(CartItem item) {
        // Check if item already exists in cart
        for (CartItem existingItem : items) {
            if (existingItem.getBookId() == item.getBookId()) {
                existingItem.setQuantity(existingItem.getQuantity() + item.getQuantity());
                return;
            }
        }
        // If item doesn't exist, add it
        items.add(item);
    }
    
    public void updateItem(int bookId, int quantity) {
        for (CartItem item : items) {
            if (item.getBookId() == bookId) {
                item.setQuantity(quantity);
                return;
            }
        }
    }
    
    public void removeItem(int bookId) {
        items.removeIf(item -> item.getBookId() == bookId);
    }
    
    public void clear() {
        items.clear();
    }
}