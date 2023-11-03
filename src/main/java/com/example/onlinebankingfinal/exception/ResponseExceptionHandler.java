package com.example.onlinebankingfinal.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ResponseExceptionHandler {

    @ExceptionHandler({NotFoundException.class})
    public ResponseEntity<ErrorResponse> exceptionHandler(Throwable ex){
        ErrorResponse response = ErrorResponse.builder(ex, HttpStatus.NOT_FOUND, ex.getMessage()).build();
        return ResponseEntity.ofNullable(response);
    }
}
