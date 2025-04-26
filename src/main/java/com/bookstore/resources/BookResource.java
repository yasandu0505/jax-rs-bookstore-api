package com.bookstore.resources;

import com.bookstore.models.Book;
import com.bookstore.services.BookService;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.List;

@Path("/books")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BookResource {
    private BookService bookService;
    
    public BookResource() {
        this.bookService = new BookService();
    }
    
    @POST
    public Response createBook(Book book, @Context UriInfo uriInfo) {
        Book createdBook = bookService.createBook(book);
        URI uri = uriInfo.getAbsolutePathBuilder().path(String.valueOf(createdBook.getId())).build();
        return Response.created(uri).entity(createdBook).build();
    }
    
    @GET
    public Response getAllBooks() {
        List<Book> books = bookService.getAllBooks();
        return Response.ok(books).build();
    }
    
    @GET
    @Path("/{id}")
    public Response getBookById(@PathParam("id") int id) {
        Book book = bookService.getBookById(id);
        return Response.ok(book).build();
    }
    
    @PUT
    @Path("/{id}")
    public Response updateBook(@PathParam("id") int id, Book book) {
        Book updatedBook = bookService.updateBook(id, book);
        return Response.ok(updatedBook).build();
    }
    
    @DELETE
    @Path("/{id}")
    public Response deleteBook(@PathParam("id") int id) {
        bookService.deleteBook(id);
        return Response.noContent().build();
    }
}