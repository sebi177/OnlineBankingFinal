package com.example.onlinebankingfinal.model.enums;

import lombok.Getter;

@Getter
public enum CurrencyCode {
    AUD(0.64),
    CAD(0.68),
    CNY(0.13),
    EUR(1.0),
    GBP(1.17),
    JPY(0.008),
    USD(0.85);

    private final double exchangeRateToEUR;

    CurrencyCode(double exchangeRateToEUR) {
        this.exchangeRateToEUR = exchangeRateToEUR;
    }

}
