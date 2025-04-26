package com.bookstore.resources;

import com.bookstore.model.Author;
import com.bookstore.model.Book;
import com.bookstore.storage.DataStore;
import com.bookstore.exception.AuthorNotFoundException;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

@Path("/authors")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AuthorResource {

    @POST
    public Author createAuthor(Author author) {
        int id = DataStore.generateAuthorId();
        author.setId(id);
        DataStore.authors.put(id, author);
        return author;
    }

    @GET
    public List<Author> getAllAuthors() {
        return new ArrayList<>(DataStore.authors.values());
    }

    @GET
    @Path("/{id}")
    public Author getAuthorById(@PathParam("id") int id) {
        Author author = DataStore.authors.get(id);
        if (author == null) {
            throw new AuthorNotFoundException("Author with ID " + id + " not found");
        }
        return author;
    }

    @PUT
    @Path("/{id}")
    public Author updateAuthor(@PathParam("id") int id, Author updatedAuthor) {
        Author existing = DataStore.authors.get(id);
        if (existing == null) {
            throw new AuthorNotFoundException("Author with ID " + id + " not found");
        }
        updatedAuthor.setId(id);
        DataStore.authors.put(id, updatedAuthor);
        return updatedAuthor;
    }

    @DELETE
    @Path("/{id}")
    public void deleteAuthor(@PathParam("id") int id) {
        Author removed = DataStore.authors.remove(id);
        if (removed == null) {
            throw new AuthorNotFoundException("Author with ID " + id + " not found");
        }
    }

    @GET
    @Path("/{id}/books")
    public List<Book> getBooksByAuthor(@PathParam("id") int id) {
        List<Book> booksByAuthor = new ArrayList<>();
        for (Book book : DataStore.books.values()) {
            if (book.getAuthorId() == id) {
                booksByAuthor.add(book);
            }
        }
        return booksByAuthor;
    }
}
