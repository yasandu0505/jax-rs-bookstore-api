package com.bookstore.database;

import com.bookstore.models.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class InMemoryDatabase {
    private static InMemoryDatabase instance;
    
    private Map<Integer, Book> books;
    private Map<Integer, Author> authors;
    private Map<Integer, Customer> customers;
    private Map<Integer, Cart> carts;
    private Map<Integer, Order> orders;
    
    private int bookIdCounter;
    private int authorIdCounter;
    private int customerIdCounter;
    private int orderIdCounter;
    
    private InMemoryDatabase() {
        books = new HashMap<>();
        authors = new HashMap<>();
        customers = new HashMap<>();
        carts = new HashMap<>();
        orders = new HashMap<>();
        
        bookIdCounter = 1;
        authorIdCounter = 1;
        customerIdCounter = 1;
        orderIdCounter = 1;
        
        // Initialize with some sample data
        initializeSampleData();
    }
    
    public static synchronized InMemoryDatabase getInstance() {
        if (instance == null) {
            instance = new InMemoryDatabase();
        }
        return instance;
    }
    
    private void initializeSampleData() {
        // Add sample authors
        Author author1 = new Author(authorIdCounter++, "J.K. Rowling", "British author best known for the Harry Potter series.");
        Author author2 = new Author(authorIdCounter++, "George Orwell", "English novelist, essayist, journalist, and critic.");
        authors.put(author1.getId(), author1);
        authors.put(author2.getId(), author2);
        
        // Add sample books
        Book book1 = new Book(bookIdCounter++, "Harry Potter and the Philosopher's Stone", author1.getId(), "978-0-7475-3269-9", 1997, 15.99, 50);
        Book book2 = new Book(bookIdCounter++, "1984", author2.getId(), "978-0-14-118776-1", 1949, 12.99, 30);
        books.put(book1.getId(), book1);
        books.put(book2.getId(), book2);
        
        // Add sample customer
        Customer customer = new Customer(customerIdCounter++, "John Doe", "john@example.com", "password123");
        customers.put(customer.getId(), customer);
        
        // Add sample cart
        Cart cart = new Cart(customer.getId());
        carts.put(customer.getId(), cart);
    }
    
    // Book methods
    public Book addBook(Book book) {
        book.setId(bookIdCounter++);
        books.put(book.getId(), book);
        return book;
    }
    
    public List<Book> getAllBooks() {
        return new ArrayList<>(books.values());
    }
    
    public Book getBookById(int id) {
        return books.get(id);
    }
    
    public Book updateBook(int id, Book book) {
        if (books.containsKey(id)) {
            book.setId(id);
            books.put(id, book);
            return book;
        }
        return null;
    }
    
    public boolean deleteBook(int id) {
        return books.remove(id) != null;
    }
    
    public List<Book> getBooksByAuthor(int authorId) {
        return books.values().stream()
                .filter(book -> book.getAuthorId() == authorId)
                .collect(Collectors.toList());
    }
    
    // Author methods
    public Author addAuthor(Author author) {
        author.setId(authorIdCounter++);
        authors.put(author.getId(), author);
        return author;
    }
    
    public List<Author> getAllAuthors() {
        return new ArrayList<>(authors.values());
    }
    
    public Author getAuthorById(int id) {
        return authors.get(id);
    }
    
    public Author updateAuthor(int id, Author author) {
        if (authors.containsKey(id)) {
            author.setId(id);
            authors.put(id, author);
            return author;
        }
        return null;
    }
    
    public boolean deleteAuthor(int id) {
        return authors.remove(id) != null;
    }
    
    // Customer methods
    public Customer addCustomer(Customer customer) {
        customer.setId(customerIdCounter++);
        customers.put(customer.getId(), customer);
        return customer;
    }
    
    public List<Customer> getAllCustomers() {
        return new ArrayList<>(customers.values());
    }
    
    public Customer getCustomerById(int id) {
        return customers.get(id);
    }
    
    public Customer updateCustomer(int id, Customer customer) {
        if (customers.containsKey(id)) {
            customer.setId(id);
            customers.put(id, customer);
            return customer;
        }
        return null;
    }
    
    public boolean deleteCustomer(int id) {
        return customers.remove(id) != null;
    }
    
    // Cart methods
    public Cart getCart(int customerId) {
        return carts.get(customerId);
    }
    
    public Cart createCart(int customerId) {
        Cart cart = new Cart(customerId);
        carts.put(customerId, cart);
        return cart;
    }
    
    public void updateCart(Cart cart) {
        carts.put(cart.getCustomerId(), cart);
    }
    
    public boolean deleteCart(int customerId) {
        return carts.remove(customerId) != null;
    }
    
    // Order methods
    public Order createOrder(Order order) {
        order.setId(orderIdCounter++);
        orders.put(order.getId(), order);
        return order;
    }
    
    public List<Order> getOrdersByCustomer(int customerId) {
        return orders.values().stream()
                .filter(order -> order.getCustomerId() == customerId)
                .collect(Collectors.toList());
    }
    
    public Order getOrderById(int orderId) {
        return orders.get(orderId);
    }
}