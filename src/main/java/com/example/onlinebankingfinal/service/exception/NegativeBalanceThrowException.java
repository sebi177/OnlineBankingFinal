package com.example.onlinebankingfinal.service.exception;

public class NegativeBalanceThrowException extends RuntimeException {
    public NegativeBalanceThrowException(String message){
        super(message);
    }
}
