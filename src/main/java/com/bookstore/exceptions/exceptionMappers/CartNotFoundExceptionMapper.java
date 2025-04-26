package com.bookstore.exception;

import com.bookstore.exception.CartNotFoundException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class CartNotFoundExceptionMapper implements ExceptionMapper<CartNotFoundException> {
    @Override
    public Response toResponse(CartNotFoundException e) {
        return Response.status(Response.Status.NOT_FOUND)
                       .entity(new ErrorMessage(e.getMessage(), 404))
                       .build();
    }
}
