package com.bookstore.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Book {
    private int id;
    private String title;
    private Author author;  // Use Author object instead of just String
    private String isbn;
    private int publicationYear;
    private double price;
    private int stockQuantity;

    public Book() {
    }

    // All-args constructor
    public Book(int id, String title, Author author, String isbn, int publicationYear, double price, int stockQuantity) {
        this.id = id;
        this.title = title;
        this.author = author;  // Set Author object
        this.isbn = isbn;
        this.publicationYear = publicationYear;
        this.price = price;
        this.stockQuantity = stockQuantity;
    }

    // Getters and Setters (Important for JAX-RS JSON mapping)

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public int getAuthorId() {
        return author != null ? author.getId() : -1;  // Return author's ID, or -1 if author is null
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    // Optionally override toString() for debugging
    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author=" + (author != null ? author.getName() : "N/A") +
                ", isbn='" + isbn + '\'' +
                ", publicationYear=" + publicationYear +
                ", price=" + price +
                ", stockQuantity=" + stockQuantity +
                '}';
    }
}
