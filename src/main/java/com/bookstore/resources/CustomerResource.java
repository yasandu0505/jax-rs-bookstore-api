package com.bookstore.resources;

import com.bookstore.model.Customer;
import com.bookstore.storage.DataStore;
import com.bookstore.exception.CustomerNotFoundException;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

@Path("/customers")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CustomerResource {

    @POST
    public Customer createCustomer(Customer customer) {
        int id = DataStore.generateCustomerId();
        customer.setId(id);
        DataStore.customers.put(id, customer);
        return customer;
    }

    @GET
    public List<Customer> getAllCustomers() {
        return new ArrayList<>(DataStore.customers.values());
    }

    @GET
    @Path("/{id}")
    public Customer getCustomerById(@PathParam("id") int id) {
        Customer customer = DataStore.customers.get(id);
        if (customer == null) {
            throw new CustomerNotFoundException("Customer with ID " + id + " not found");
        }
        return customer;
    }

    @PUT
    @Path("/{id}")
    public Customer updateCustomer(@PathParam("id") int id, Customer updatedCustomer) {
        Customer existing = DataStore.customers.get(id);
        if (existing == null) {
            throw new CustomerNotFoundException("Customer with ID " + id + " not found");
        }
        updatedCustomer.setId(id);
        DataStore.customers.put(id, updatedCustomer);
        return updatedCustomer;
    }

    @DELETE
    @Path("/{id}")
    public void deleteCustomer(@PathParam("id") int id) {
        Customer removed = DataStore.customers.remove(id);
        if (removed == null) {
            throw new CustomerNotFoundException("Customer with ID " + id + " not found");
        }
    }
}
