package com.example.onlinebankingfinal.dto;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
public class CardDTO {
    private String cardType;
    private String cardNumber;
    private String cardHolder;
    private String cvv;
}
