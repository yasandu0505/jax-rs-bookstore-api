package com.bookstore.services;

import com.bookstore.database.InMemoryDatabase;
import com.bookstore.exceptions.*;
import com.bookstore.models.*;

import java.util.ArrayList;
import java.util.List;

public class OrderService {
    private InMemoryDatabase database;
    private CartService cartService;
    
    public OrderService() {
        this.database = InMemoryDatabase.getInstance();
        this.cartService = new CartService();
    }
    
    public Order createOrder(int customerId) {
        // Check if customer exists
        Customer customer = database.getCustomerById(customerId);
        if (customer == null) {
            throw new CustomerNotFoundException("Customer with ID " + customerId + " does not exist.");
        }
        
        // Check if cart exists and is not empty
        Cart cart = database.getCart(customerId);
        if (cart == null || cart.getItems().isEmpty()) {
            throw new CartNotFoundException("Cart for customer with ID " + customerId + " does not exist or is empty.");
        }
        
        // Create order
        Order order = new Order();
        order.setCustomerId(customerId);
        
        // Add items to order
        List<OrderItem> orderItems = new ArrayList<>();
        for (CartItem cartItem : cart.getItems()) {
            Book book = database.getBookById(cartItem.getBookId());
            if (book == null) {
                throw new BookNotFoundException("Book with ID " + cartItem.getBookId() + " does not exist.");
            }
            
            // Check if enough stock
            if (book.getStock() < cartItem.getQuantity()) {
                throw new OutOfStockException("Not enough stock for book with ID " + cartItem.getBookId() + 
                        ". Available: " + book.getStock() + ", Requested: " + cartItem.getQuantity());
            }
            
            // Create order item
            OrderItem orderItem = new OrderItem(cartItem.getBookId(), book.getTitle(), book.getPrice(), cartItem.getQuantity());
            orderItems.add(orderItem);
            
            // Update book stock
            book.setStock(book.getStock() - cartItem.getQuantity());
            database.updateBook(book.getId(), book);
        }
        
        order.setItems(orderItems);
        
        // Save order
        Order savedOrder = database.createOrder(order);
        
        // Clear cart
        cartService.clearCart(customerId);
        
        return savedOrder;
    }
    
    public List<Order> getOrdersByCustomer(int customerId) {
        // Check if customer exists
        Customer customer = database.getCustomerById(customerId);
        if (customer == null) {
            throw new CustomerNotFoundException("Customer with ID " + customerId + " does not exist.");
        }
        
        return database.getOrdersByCustomer(customerId);
    }
    
    public Order getOrderById(int customerId, int orderId) {
        // Check if customer exists
        Customer customer = database.getCustomerById(customerId);
        if (customer == null) {
            throw new CustomerNotFoundException("Customer with ID " + customerId + " does not exist.");
        }
        
        // Check if order exists
        Order order = database.getOrderById(orderId);
        if (order == null) {
            throw new InvalidInputException("Order with ID " + orderId + " does not exist.");
        }
        
        // Check if order belongs to customer
        if (order.getCustomerId() != customerId) {
            throw new InvalidInputException("Order with ID " + orderId + " does not belong to customer with ID " + customerId);
        }
        
        return order;
    }
    
    public void deleteOrder(int customerId, int orderId) {
    // Check if customer exists
    Customer customer = database.getCustomerById(customerId);
    if (customer == null) {
        throw new CustomerNotFoundException("Customer with ID " + customerId + " does not exist.");
    }
    
    // Check if order exists
    Order order = database.getOrderById(orderId);
    if (order == null) {
        throw new InvalidInputException("Order with ID " + orderId + " does not exist.");
    }
    
    // Check if order belongs to customer
    if (order.getCustomerId() != customerId) {
        throw new InvalidInputException("Order with ID " + orderId + " does not belong to customer with ID " + customerId);
    }
    
    // Delete the order
    database.deleteOrder(orderId);
}
    
    
}