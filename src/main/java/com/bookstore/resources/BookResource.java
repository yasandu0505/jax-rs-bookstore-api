package com.bookstore.resources;

import com.bookstore.models.Book;
import com.bookstore.database.DataStore;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.*;

@Path("/books")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BookResource {

    @POST
    public Response addBook(Book book) {
        System.out.println("GET /books called post");
        int id = DataStore.books.size() + 1;
        book.setId(id);
        DataStore.books.put(id, book);
        return Response.status(Response.Status.CREATED).entity(book).build();
    }

    @GET
    public Response getAllBooks() {
        System.out.println("GET /books called");
        Collection<Book> books = DataStore.books.values();
        return Response.ok(new ArrayList<>(books)).build();
    }

    @GET
    @Path("/{id}")
    public Response getBookById(@PathParam("id") int id) {
        Book book = DataStore.books.get(id);
        if (book == null) {
            throw new NotFoundException("Book not found with id: " + id);
        }
        return Response.ok(book).build();
    }

    @PUT
    @Path("/{id}")
    public Response updateBook(@PathParam("id") int id, Book updatedBook) {
        Book book = DataStore.books.get(id);
        if (book == null) {
            throw new NotFoundException("Book not found with id: " + id);
        }
        updatedBook.setId(id);
        DataStore.books.put(id, updatedBook);
        return Response.ok(updatedBook).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteBook(@PathParam("id") int id) {
        Book book = DataStore.books.remove(id);
        if (book == null) {
            throw new NotFoundException("Book not found with id: " + id);
        }
        return Response.noContent().build();
    }
}
