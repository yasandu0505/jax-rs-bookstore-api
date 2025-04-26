package com.bookstore.resources;

import com.bookstore.models.Customer;
import com.bookstore.services.CustomerService;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.List;

@Path("/customers")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CustomerResource {
    private CustomerService customerService;
    
    public CustomerResource() {
        this.customerService = new CustomerService();
    }
    
    @POST
    public Response createCustomer(Customer customer, @Context UriInfo uriInfo) {
        Customer createdCustomer = customerService.createCustomer(customer);
        URI uri = uriInfo.getAbsolutePathBuilder().path(String.valueOf(createdCustomer.getId())).build();
        return Response.created(uri).entity(createdCustomer).build();
    }
    
    @GET
    public Response getAllCustomers() {
        List<Customer> customers = customerService.getAllCustomers();
        return Response.ok(customers).build();
    }
    
    @GET
    @Path("/{id}")
    public Response getCustomerById(@PathParam("id") int id) {
        Customer customer = customerService.getCustomerById(id);
        return Response.ok(customer).build();
    }
    
    @PUT
    @Path("/{id}")
    public Response updateCustomer(@PathParam("id") int id, Customer customer) {
        Customer updatedCustomer = customerService.updateCustomer(id, customer);
        return Response.ok(updatedCustomer).build();
    }
    
    @DELETE
    @Path("/{id}")
    public Response deleteCustomer(@PathParam("id") int id) {
        customerService.deleteCustomer(id);
        return Response.noContent().build();
    }
}