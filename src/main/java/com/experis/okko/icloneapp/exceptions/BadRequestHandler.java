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
 * Handler for HTTP bad request (402)
 * 402 is thrown if API request contains invalid parameters.
 *
 */

@ControllerAdvice
public class BadRequestHandler {
    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    //Response with error message and BAD_REQUEST status. Logging & proper JSON response could be added here..
    public String handleBadRequest(BadRequestException e) {
        return "Bad data formatting. Couldn't create or update Customer.";
    }}




