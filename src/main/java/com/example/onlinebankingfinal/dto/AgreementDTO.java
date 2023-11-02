package com.example.onlinebankingfinal.dto;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
public class AgreementDTO {

    private String interestRate;
    private String agreementStatus;
    private String agreementSum;
}
