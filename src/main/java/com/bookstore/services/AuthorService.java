package com.bookstore.services;

import com.bookstore.database.InMemoryDatabase;
import com.bookstore.exceptions.AuthorNotFoundException;
import com.bookstore.exceptions.InvalidInputException;
import com.bookstore.models.Author;
import com.bookstore.models.Book;

import java.util.List;

public class AuthorService {
    private InMemoryDatabase database;
    
    public AuthorService() {
        this.database = InMemoryDatabase.getInstance();
    }
    
    public Author createAuthor(Author author) {
        validateAuthor(author);
        return database.addAuthor(author);
    }
    
    public List<Author> getAllAuthors() {
        return database.getAllAuthors();
    }
    
    public Author getAuthorById(int id) {
        Author author = database.getAuthorById(id);
        if (author == null) {
            throw new AuthorNotFoundException("Author with ID " + id + " does not exist.");
        }
        return author;
    }
    
    public Author updateAuthor(int id, Author author) {
        // Check if author exists
        if (database.getAuthorById(id) == null) {
            throw new AuthorNotFoundException("Author with ID " + id + " does not exist.");
        }
        
        validateAuthor(author);
        
        return database.updateAuthor(id, author);
    }
    
    public void deleteAuthor(int id) {
        if (database.getAuthorById(id) == null) {
            throw new AuthorNotFoundException("Author with ID " + id + " does not exist.");
        }
        
        // Check if author has books
        List<Book> authorBooks = database.getBooksByAuthor(id);
        if (!authorBooks.isEmpty()) {
            throw new InvalidInputException("Cannot delete author with existing books. Remove the books first.");
        }
        
        database.deleteAuthor(id);
    }
    
    public List<Book> getBooksByAuthor(int authorId) {
        // Check if author exists
        if (database.getAuthorById(authorId) == null) {
            throw new AuthorNotFoundException("Author with ID " + authorId + " does not exist.");
        }
        
        return database.getBooksByAuthor(authorId);
    }
    
    private void validateAuthor(Author author) {
        if (author.getName() == null || author.getName().trim().isEmpty()) {
            throw new InvalidInputException("Author name cannot be empty.");
        }
    }
}