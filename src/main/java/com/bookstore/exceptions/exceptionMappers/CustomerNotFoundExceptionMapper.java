package com.bookstore.exception;

import com.bookstore.exception.CustomerNotFoundException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class CustomerNotFoundExceptionMapper implements ExceptionMapper<CustomerNotFoundException> {
    @Override
    public Response toResponse(CustomerNotFoundException e) {
        return Response.status(Response.Status.NOT_FOUND)
                       .entity(new ErrorMessage(e.getMessage(), 404))
                       .build();
    }
}
