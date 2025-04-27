package com.bookstore.resources;

import com.bookstore.models.Order;
import com.bookstore.services.OrderService;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.List;

@Path("/customers/{customerId}/orders")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class OrderResource {
    private OrderService orderService;
    
    public OrderResource() {
        this.orderService = new OrderService();
    }
    
    @POST
    public Response createOrder(@PathParam("customerId") int customerId, @Context UriInfo uriInfo) {
        Order createdOrder = orderService.createOrder(customerId);
        URI uri = uriInfo.getAbsolutePathBuilder().path(String.valueOf(createdOrder.getId())).build();
        return Response.created(uri).entity(createdOrder).build();
    }
    
    @GET
    public Response getOrdersByCustomer(@PathParam("customerId") int customerId) {
        List<Order> orders = orderService.getOrdersByCustomer(customerId);
        return Response.ok(orders).build();
    }
    
    @GET
    @Path("/{orderId}")
    public Response getOrderById(
            @PathParam("customerId") int customerId,
            @PathParam("orderId") int orderId) {
        Order order = orderService.getOrderById(customerId, orderId);
        return Response.ok(order).build();
    }
    
    @DELETE
    @Path("/{orderId}")
    public Response deleteOrder(
        @PathParam("customerId") int customerId,
        @PathParam("orderId") int orderId) {
    orderService.deleteOrder(customerId, orderId);
    return Response.noContent().build();
}
    
    
}