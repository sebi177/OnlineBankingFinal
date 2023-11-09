package com.example.onlinebankingfinal.controller.handler;

import com.example.onlinebankingfinal.dto.ErrorDTO;
import com.example.onlinebankingfinal.service.exception.AccountNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.net.HttpURLConnection;

@ControllerAdvice
public class ResponseExceptionHandler {

//    @ExceptionHandler({AccountNotFoundException.class})
//    public ResponseEntity<ErrorDTO> handleAccountNotFoundException(AccountNotFoundException ex){
//        ErrorDTO response = new ErrorDTO(HttpURLConnection.HTTP_NOT_FOUND, ex.getMessage());
//        return ResponseEntity.status(HttpURLConnection.HTTP_NOT_FOUND).body(response);
//    }


}
