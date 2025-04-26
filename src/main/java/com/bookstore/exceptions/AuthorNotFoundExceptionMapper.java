package com.bookstore.exception;

import com.bookstore.exception.AuthorNotFoundException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class AuthorNotFoundExceptionMapper implements ExceptionMapper<AuthorNotFoundException> {
    @Override
    public Response toResponse(AuthorNotFoundException e) {
        return Response.status(Response.Status.NOT_FOUND)
                       .entity(new ErrorMessage(e.getMessage(), 404))
                       .build();
    }
}
