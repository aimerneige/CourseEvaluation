package com.aimerneige.course_evaluation.exceptions.resolvers;

import javax.servlet.http.HttpServletRequest;

import com.aimerneige.course_evaluation.response.Response;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class NotFoundResourceExceptionResolver {

    @ExceptionHandler(NoHandlerFoundException.class)
    public Response handleNotFoundResourceException(HttpServletRequest request,
            NoHandlerFoundException e) {
        Response response = Response.notFound();
        response.setMessage("Requested resource wasn't found on the server");
        response.setData(e.getMessage());
        return response;
    }
}
