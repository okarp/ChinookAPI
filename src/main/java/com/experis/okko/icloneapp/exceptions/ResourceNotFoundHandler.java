package com.experis.okko.icloneapp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/*
 * Experis Academy Task 4
 * Okko Partanen
 *
 * Handler for HTTP not found (404)
 * 404 is thrown when requesting resource that does not exist
 *
 */

@ControllerAdvice
public class ResourceNotFoundHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    //Response with error message and NOT_FOUND status. Logging & proper JSON response could be added here..
    public String handleResourceNotFound() {
        return "Customer ID was not found in database.";
    }
}
