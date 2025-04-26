package com.bookstore.exception;

import com.bookstore.exception.OutOfStockException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class OutOfStockExceptionMapper implements ExceptionMapper<OutOfStockException> {
    @Override
    public Response toResponse(OutOfStockException e) {
        return Response.status(Response.Status.BAD_REQUEST)
                       .entity(new ErrorMessage(e.getMessage(), 400))
                       .build();
    }
}
