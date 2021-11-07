package com.company.sample.controller;

import com.company.sample.exceptions.InvalidTransactionException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TransactionAdvice {

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value= InvalidTransactionException.class)
    Error catchBadRequest(InvalidTransactionException e) {
        return new Error(e.getMessage());
    }

    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value= Exception.class)
    Error catchAllExceptions(Exception e) {
        return new Error(e.getMessage());
    }
}
