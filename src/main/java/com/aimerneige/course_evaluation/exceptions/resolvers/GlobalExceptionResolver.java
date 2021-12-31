package com.aimerneige.course_evaluation.exceptions.resolvers;

import javax.servlet.http.HttpServletRequest;

import com.aimerneige.course_evaluation.response.Response;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionResolver {

    @ExceptionHandler(Exception.class)
    public Response handleException(HttpServletRequest request, Exception e) {
        Response response = Response.internalServerError();
        response.setMessage("Internal server error");
        response.setData(e.getMessage());
        return response;
    }
}
