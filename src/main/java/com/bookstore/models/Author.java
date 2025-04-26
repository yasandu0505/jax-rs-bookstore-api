package com.bookstore.models;

public class Author {
    private int id;
    private String name;
    private String biography;
    
    // Default constructor required for JAX-RS
    public Author() {
    }
    
    public Author(int id, String name, String biography) {
        this.id = id;
        this.name = name;
        this.biography = biography;
    }
    
    // Getters and Setters
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getBiography() {
        return biography;
    }
    
    public void setBiography(String biography) {
        this.biography = biography;
    }
}