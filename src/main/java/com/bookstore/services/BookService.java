package com.bookstore.services;

import com.bookstore.database.InMemoryDatabase;
import com.bookstore.exceptions.AuthorNotFoundException;
import com.bookstore.exceptions.BookNotFoundException;
import com.bookstore.exceptions.InvalidInputException;
import com.bookstore.models.Book;

import java.time.LocalDate;
import java.util.List;

public class BookService {
    private InMemoryDatabase database;
    
    public BookService() {
        this.database = InMemoryDatabase.getInstance();
    }
    
    public Book createBook(Book book) {
        validateBook(book);
        
        if (database.getAuthorById(book.getAuthorId()) == null) {
            throw new AuthorNotFoundException("Author with ID " + book.getAuthorId() + " does not exist.");
        }
        
        return database.addBook(book);
    }
    
    public List<Book> getAllBooks() {
        return database.getAllBooks();
    }
    
    public Book getBookById(int id) {
        Book book = database.getBookById(id);
        if (book == null) {
            throw new BookNotFoundException("Book with ID " + id + " does not exist.");
        }
        return book;
    }
    
    public Book updateBook(int id, Book book) {
        // Check if book exists
        if (database.getBookById(id) == null) {
            throw new BookNotFoundException("Book with ID " + id + " does not exist.");
        }
        
        validateBook(book);
        
        // Check if author exists
        if (database.getAuthorById(book.getAuthorId()) == null) {
            throw new AuthorNotFoundException("Author with ID " + book.getAuthorId() + " does not exist.");
        }
        
        return database.updateBook(id, book);
    }
    
    public void deleteBook(int id) {
        if (database.getBookById(id) == null) {
            throw new BookNotFoundException("Book with ID " + id + " does not exist.");
        }
        
        database.deleteBook(id);
    }
    
    private void validateBook(Book book) {
        if (book.getTitle() == null || book.getTitle().trim().isEmpty()) {
            throw new InvalidInputException("Book title cannot be empty.");
        }
        
        if (book.getIsbn() == null || book.getIsbn().trim().isEmpty()) {
            throw new InvalidInputException("Book ISBN cannot be empty.");
        }
        
        if (book.getPublicationYear() <= 0) {
            throw new InvalidInputException("Publication year must be positive.");
        }
        
        // Check that publication year is not in the future
        int currentYear = LocalDate.now().getYear();
        if (book.getPublicationYear() > currentYear) {
            throw new InvalidInputException("Publication year cannot be in the future.");
        }
        
        if (book.getPrice() < 0) {
            throw new InvalidInputException("Book price cannot be negative.");
        }
        
        if (book.getStock() < 0) {
            throw new InvalidInputException("Book stock cannot be negative.");
        }
    }
}