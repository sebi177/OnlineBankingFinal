package com.example.onlinebankingfinal.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CardDTO {
    private String cardType;
    private String cardNumber;
    private String cardHolder;
    private String cvv;
}
