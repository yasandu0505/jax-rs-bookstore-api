package com.bookstore.resources;

import com.bookstore.models.CartItem;
import com.bookstore.models.Customer;
import com.bookstore.models.Order;
import com.bookstore.models.Book; // Add this import
import com.bookstore.database.DataStore;
import com.bookstore.exceptions.CustomerNotFoundException;
import com.bookstore.exceptions.OutOfStockException;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

@Path("/customers/{customerId}/orders")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class OrderResource {

    @POST
    public Order placeOrder(@PathParam("customerId") int customerId) {
        Customer customer = DataStore.customers.get(customerId);
        if (customer == null) {
            throw new CustomerNotFoundException("Customer with ID " + customerId + " not found");
        }

        List<CartItem> cartItems = customer.getCart();
        if (cartItems.isEmpty()) {
            throw new WebApplicationException("Cart is empty", 400);
        }

        double totalAmount = 0.0;
        for (CartItem item : cartItems) {
            Book book = DataStore.books.get(item.getBookId());  // Change var to Book
            if (book == null) {
                throw new WebApplicationException("Book with ID " + item.getBookId() + " not found", 404);
            }
            if (book.getStockQuantity() < item.getQuantity()) {
                throw new OutOfStockException("Not enough stock for book: " + book.getTitle());
            }
            book.setStockQuantity(book.getStockQuantity() - item.getQuantity());
            totalAmount += book.getPrice() * item.getQuantity();
        }

        int orderId = DataStore.generateOrderId();
        Order order = new Order(orderId, customerId, new ArrayList<>(cartItems), totalAmount);
        DataStore.orders.put(orderId, (List<Order>) order);

        // Clear customer's cart after placing order
        customer.getCart().clear();

        return order;
    }

//    @GET
//    public List<Order> getCustomerOrders(@PathParam("customerId") int customerId) {
//        List<Order> customerOrders = new ArrayList<>();
//        for (List<Order> order : DataStore.orders.values()) {
//            if (customerId == order.getCustomerId()) {
//                customerOrders.add((Order) order);
//            }
//        }
//        return customerOrders;
//    }

    @GET
    @Path("/{orderId}")
    public Order getOrderById(@PathParam("customerId") int customerId, @PathParam("orderId") int orderId) {
        Order order = (Order) DataStore.orders.get(orderId);
        if (order == null || order.getCustomerId() != customerId) {
            throw new WebApplicationException("Order not found for this customer", 404);
        }
        return order;
    }
}
