package com.bookstore.services;

import com.bookstore.database.InMemoryDatabase;
import com.bookstore.exceptions.CustomerNotFoundException;
import com.bookstore.exceptions.InvalidInputException;
import com.bookstore.models.Customer;

import java.util.List;
import java.util.regex.Pattern;

public class CustomerService {
    private InMemoryDatabase database;
    
    public CustomerService() {
        this.database = InMemoryDatabase.getInstance();
    }
    
    public Customer createCustomer(Customer customer) {
        validateCustomer(customer);
        return database.addCustomer(customer);
    }
    
    public List<Customer> getAllCustomers() {
        return database.getAllCustomers();
    }
    
    public Customer getCustomerById(int id) {
        Customer customer = database.getCustomerById(id);
        if (customer == null) {
            throw new CustomerNotFoundException("Customer with ID " + id + " does not exist.");
        }
        return customer;
    }
    
    public Customer updateCustomer(int id, Customer customer) {
        // Check if customer exists
        if (database.getCustomerById(id) == null) {
            throw new CustomerNotFoundException("Customer with ID " + id + " does not exist.");
        }
        
        validateCustomer(customer);
        
        return database.updateCustomer(id, customer);
    }
    
    public void deleteCustomer(int id) {
        if (database.getCustomerById(id) == null) {
            throw new CustomerNotFoundException("Customer with ID " + id + " does not exist.");
        }
        
        database.deleteCustomer(id);
    }
    
    private void validateCustomer(Customer customer) {
        if (customer.getName() == null || customer.getName().trim().isEmpty()) {
            throw new InvalidInputException("Customer name cannot be empty.");
        }
        
        if (customer.getEmail() == null || customer.getEmail().trim().isEmpty()) {
            throw new InvalidInputException("Customer email cannot be empty.");
        }
        
        // Simple email validation using regex
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        if (!Pattern.compile(emailRegex).matcher(customer.getEmail()).matches()) {
            throw new InvalidInputException("Invalid email format.");
        }
        
        if (customer.getPassword() == null || customer.getPassword().trim().isEmpty()) {
            throw new InvalidInputException("Customer password cannot be empty.");
        }
        
        if (customer.getPassword().length() < 6) {
            throw new InvalidInputException("Password must be at least 6 characters long.");
        }
    }
}