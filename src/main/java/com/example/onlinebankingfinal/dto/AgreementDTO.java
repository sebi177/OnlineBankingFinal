package com.example.onlinebankingfinal.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AgreementDTO {

    private String interestRate;
    private String agreementStatus;
    private String agreementSum;
}
