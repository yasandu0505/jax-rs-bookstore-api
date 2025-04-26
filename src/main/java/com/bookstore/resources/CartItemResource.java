package com.bookstore.resources;

import com.bookstore.model.CartItem;
import com.bookstore.model.Customer;
import com.bookstore.storage.DataStore;
import com.bookstore.exception.CustomerNotFoundException;
import com.bookstore.exception.CartNotFoundException;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/customers/{customerId}/cart")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CartItemResource {

    @POST
    @Path("/items")
    public CartItem addItemToCart(@PathParam("customerId") int customerId, CartItem item) {
        Customer customer = DataStore.customers.get(customerId);
        if (customer == null) {
            throw new CustomerNotFoundException("Customer with ID " + customerId + " not found");
        }
        customer.getCart().add(item);
        return item;
    }

    @GET
    public List<CartItem> getCartItems(@PathParam("customerId") int customerId) {
        Customer customer = DataStore.customers.get(customerId);
        if (customer == null) {
            throw new CustomerNotFoundException("Customer with ID " + customerId + " not found");
        }
        return customer.getCart();
    }

    @PUT
    @Path("/items/{bookId}")
    public CartItem updateCartItem(@PathParam("customerId") int customerId, @PathParam("bookId") int bookId, CartItem updatedItem) {
        Customer customer = DataStore.customers.get(customerId);
        if (customer == null) {
            throw new CustomerNotFoundException("Customer with ID " + customerId + " not found");
        }
        for (CartItem item : customer.getCart()) {
            if (item.getBookId() == bookId) {
                item.setQuantity(updatedItem.getQuantity());
                return item;
            }
        }
        throw new CartNotFoundException("Cart item with Book ID " + bookId + " not found");
    }

    @DELETE
    @Path("/items/{bookId}")
    public void deleteCartItem(@PathParam("customerId") int customerId, @PathParam("bookId") int bookId) {
        Customer customer = DataStore.customers.get(customerId);
        if (customer == null) {
            throw new CustomerNotFoundException("Customer with ID " + customerId + " not found");
        }
        boolean removed = customer.getCart().removeIf(item -> item.getBookId() == bookId);
        if (!removed) {
            throw new CartNotFoundException("Cart item with Book ID " + bookId + " not found");
        }
    }
}
