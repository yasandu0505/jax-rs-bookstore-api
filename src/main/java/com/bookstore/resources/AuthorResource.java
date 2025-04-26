package com.bookstore.resources;

import com.bookstore.models.Author;
import com.bookstore.models.Book;
import com.bookstore.services.AuthorService;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.List;

@Path("/authors")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AuthorResource {
    private AuthorService authorService;
    
    public AuthorResource() {
        this.authorService = new AuthorService();
    }
    
    @POST
    public Response createAuthor(Author author, @Context UriInfo uriInfo) {
        Author createdAuthor = authorService.createAuthor(author);
        URI uri = uriInfo.getAbsolutePathBuilder().path(String.valueOf(createdAuthor.getId())).build();
        return Response.created(uri).entity(createdAuthor).build();
    }
    
    @GET
    public Response getAllAuthors() {
        List<Author> authors = authorService.getAllAuthors();
        return Response.ok(authors).build();
    }
    
    @GET
    @Path("/{id}")
    public Response getAuthorById(@PathParam("id") int id) {
        Author author = authorService.getAuthorById(id);
        return Response.ok(author).build();
    }
    
    @PUT
    @Path("/{id}")
    public Response updateAuthor(@PathParam("id") int id, Author author) {
        Author updatedAuthor = authorService.updateAuthor(id, author);
        return Response.ok(updatedAuthor).build();
    }
    
    @DELETE
    @Path("/{id}")
    public Response deleteAuthor(@PathParam("id") int id) {
        authorService.deleteAuthor(id);
        return Response.noContent().build();
    }
    
    @GET
    @Path("/{id}/books")
    public Response getBooksByAuthor(@PathParam("id") int id) {
        List<Book> books = authorService.getBooksByAuthor(id);
        return Response.ok(books).build();
    }
}