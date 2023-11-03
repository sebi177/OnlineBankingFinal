package com.example.onlinebankingfinal.controller;

import com.example.onlinebankingfinal.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> someExceptionHandler(Throwable ex) {
        ErrorResponse response = ErrorResponse.builder(ex, HttpStatus.ACCEPTED, ex.getMessage()).build();
        return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
    }

}
