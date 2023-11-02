package com.example.onlinebankingfinal.dto;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
public class ProductDTO {
    private String productName;
    private String productStatus;
    private String productCurrencyCode;
    private String interestRate;
    private String productLimit;
}
