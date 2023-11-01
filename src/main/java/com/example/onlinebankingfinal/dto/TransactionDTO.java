package com.example.onlinebankingfinal.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TransactionDTO {
    private String transactionType;
    private String transactionAmount;
    private String transactionDescription;
}
