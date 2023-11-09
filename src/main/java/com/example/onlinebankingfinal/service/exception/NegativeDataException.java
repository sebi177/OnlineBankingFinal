package com.example.onlinebankingfinal.service.exception;

public class NegativeDataException extends RuntimeException{
    public NegativeDataException(String message){
        super(message);
    }
}
