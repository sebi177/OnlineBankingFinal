package com.example.onlinebankingfinal.dto;

import lombok.Data;

@Data
public class AccountFullDTO {
    private String accountName;
    private String accountType;
    private String accountStatus;
    private String accountBalance;
    private String accountCurrencyCode;
    private String client;
    private String card;
}
