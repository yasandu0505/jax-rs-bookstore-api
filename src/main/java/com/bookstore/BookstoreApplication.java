package com.bookstore;

import com.bookstore.exceptions.ExceptionMappers.*;
import com.bookstore.resources.*;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/api")
public class BookstoreApplication extends Application {
    
    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> classes = new HashSet<>();
        
        // Register resources
        classes.add(BookResource.class);
        classes.add(AuthorResource.class);
        classes.add(CustomerResource.class);
        classes.add(CartResource.class);
        classes.add(OrderResource.class);
        
        // Register exception mappers
        classes.add(BookNotFoundExceptionMapper.class);
        classes.add(AuthorNotFoundExceptionMapper.class);
        classes.add(CustomerNotFoundExceptionMapper.class);
        classes.add(InvalidInputExceptionMapper.class);
        classes.add(OutOfStockExceptionMapper.class);
        classes.add(CartNotFoundExceptionMapper.class);
        
        return classes;
    }
}