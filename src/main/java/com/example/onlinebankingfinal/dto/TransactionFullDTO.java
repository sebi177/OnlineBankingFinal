package com.example.onlinebankingfinal.dto;

import lombok.Data;

@Data
public class TransactionFullDTO {
    private String transactionType;
    private String transactionAmount;
    private String transactionDescription;
    private String debitAccount;
    private String creditAccount;
}
