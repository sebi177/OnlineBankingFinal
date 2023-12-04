package com.example.onlinebankingfinal.dto;

import lombok.Data;

@Data
public class TransactionCardToCard {
    private String transactionType;
    private String transactionAmount;
    private String transactionCurrencyCode;
    private String transactionDescription;
    private String debitCardNumber;
    private String creditCardNumber;
}
