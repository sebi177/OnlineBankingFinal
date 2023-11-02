package com.example.onlinebankingfinal.dto;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
public class TransactionDTO {
    private String transactionType;
    private String transactionAmount;
    private String transactionDescription;
}
