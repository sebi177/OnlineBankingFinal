package com.example.onlinebankingfinal.dto;

import lombok.Data;

@Data
public class ProductFullDTO {
    private String productName;
    private String productStatus;
    private String productCurrencyCode;
    private String interestRate;
    private String productLimit;
    private String manager;
}
