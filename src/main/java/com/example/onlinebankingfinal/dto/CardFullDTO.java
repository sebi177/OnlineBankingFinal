package com.example.onlinebankingfinal.dto;

import lombok.Data;

@Data
public class CardFullDTO {
    private String cardType;
    private String cardNumber;
    private String cardHolder;
    private String cvv;
    private String expirationDate;
    private String client;
    private String account;
}
