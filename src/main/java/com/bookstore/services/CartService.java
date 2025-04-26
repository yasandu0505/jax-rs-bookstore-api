package com.bookstore.services;

import com.bookstore.database.InMemoryDatabase;
import com.bookstore.exceptions.*;
import com.bookstore.models.Book;
import com.bookstore.models.Cart;
import com.bookstore.models.CartItem;
import com.bookstore.models.Customer;

public class CartService {
    private InMemoryDatabase database;
    
    public CartService() {
        this.database = InMemoryDatabase.getInstance();
    }
    
    public Cart getCart(int customerId) {
        // Check if customer exists
        Customer customer = database.getCustomerById(customerId);
        if (customer == null) {
            throw new CustomerNotFoundException("Customer with ID " + customerId + " does not exist.");
        }
        
        // Get or create cart
        Cart cart = database.getCart(customerId);
        if (cart == null) {
            cart = database.createCart(customerId);
        }
        
        return cart;
    }
    
    public Cart addItemToCart(int customerId, CartItem item) {
        // Check if customer exists
        Customer customer = database.getCustomerById(customerId);
        if (customer == null) {
            throw new CustomerNotFoundException("Customer with ID " + customerId + " does not exist.");
        }
        
        // Check if book exists
        Book book = database.getBookById(item.getBookId());
        if (book == null) {
            throw new BookNotFoundException("Book with ID " + item.getBookId() + " does not exist.");
        }
        
        // Check if quantity is valid
        if (item.getQuantity() <= 0) {
            throw new InvalidInputException("Quantity must be positive.");
        }
        
        // Check if enough stock
        if (book.getStock() < item.getQuantity()) {
            throw new OutOfStockException("Not enough stock for book with ID " + item.getBookId() + 
                    ". Available: " + book.getStock() + ", Requested: " + item.getQuantity());
        }
        
        // Get or create cart
        Cart cart = database.getCart(customerId);
        if (cart == null) {
            cart = database.createCart(customerId);
        }
        
        // Add item to cart
        cart.addItem(item);
        database.updateCart(cart);
        
        return cart;
    }
    
    public Cart updateCartItem(int customerId, int bookId, int quantity) {
        // Check if customer exists
        Customer customer = database.getCustomerById(customerId);
        if (customer == null) {
            throw new CustomerNotFoundException("Customer with ID " + customerId + " does not exist.");
        }
        
        // Check if book exists
        Book book = database.getBookById(bookId);
        if (book == null) {
            throw new BookNotFoundException("Book with ID " + bookId + " does not exist.");
        }
        
        // Check if cart exists
        Cart cart = database.getCart(customerId);
        if (cart == null) {
            throw new CartNotFoundException("Cart for customer with ID " + customerId + " does not exist.");
        }
        
        // Check if quantity is valid
        if (quantity <= 0) {
            throw new InvalidInputException("Quantity must be positive.");
        }
        
        // Check if enough stock
        if (book.getStock() < quantity) {
            throw new OutOfStockException("Not enough stock for book with ID " + bookId + 
                    ". Available: " + book.getStock() + ", Requested: " + quantity);
        }
        
        // Update cart item
        cart.updateItem(bookId, quantity);
        database.updateCart(cart);
        
        return cart;
    }
    
    public Cart removeCartItem(int customerId, int bookId) {
        // Check if customer exists
        Customer customer = database.getCustomerById(customerId);
        if (customer == null) {
            throw new CustomerNotFoundException("Customer with ID " + customerId + " does not exist.");
        }
        
        // Check if cart exists
        Cart cart = database.getCart(customerId);
        if (cart == null) {
            throw new CartNotFoundException("Cart for customer with ID " + customerId + " does not exist.");
        }
        
        // Remove item from cart
        cart.removeItem(bookId);
        database.updateCart(cart);
        
        return cart;
    }
    
    public void clearCart(int customerId) {
        // Check if customer exists
        Customer customer = database.getCustomerById(customerId);
        if (customer == null) {
            throw new CustomerNotFoundException("Customer with ID " + customerId + " does not exist.");
        }
        
        // Check if cart exists
        Cart cart = database.getCart(customerId);
        if (cart == null) {
            throw new CartNotFoundException("Cart for customer with ID " + customerId + " does not exist.");
        }
        
        // Clear cart
        cart.clear();
        database.updateCart(cart);
    }
}