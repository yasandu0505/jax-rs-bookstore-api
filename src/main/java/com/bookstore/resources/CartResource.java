package com.bookstore.resources;

import com.bookstore.models.Cart;
import com.bookstore.models.CartItem;
import com.bookstore.services.CartService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/customers/{customerId}/cart")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CartResource {
    private CartService cartService;
    
    public CartResource() {
        this.cartService = new CartService();
    }
    
    @GET
    public Response getCart(@PathParam("customerId") int customerId) {
        Cart cart = cartService.getCart(customerId);
        return Response.ok(cart).build();
    }
    
    @POST
    @Path("/items")
    public Response addItemToCart(@PathParam("customerId") int customerId, CartItem item) {
        Cart updatedCart = cartService.addItemToCart(customerId, item);
        return Response.ok(updatedCart).build();
    }
    
    @PUT
    @Path("/items/{bookId}")
    public Response updateCartItem(
            @PathParam("customerId") int customerId,
            @PathParam("bookId") int bookId,
            @QueryParam("quantity") int quantity) {
        Cart updatedCart = cartService.updateCartItem(customerId, bookId, quantity);
        return Response.ok(updatedCart).build();
    }
    
    @DELETE
    @Path("/items/{bookId}")
    public Response removeCartItem(
            @PathParam("customerId") int customerId,
            @PathParam("bookId") int bookId) {
        Cart updatedCart = cartService.removeCartItem(customerId, bookId);
        return Response.ok(updatedCart).build();
    }
}