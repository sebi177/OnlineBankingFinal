package com.example.onlinebankingfinal.model.enums;

import lombok.Getter;

@Getter
public enum CurrencyCode {
    AUD("Australian Dollar", 0.64),
    CAD("Canadian Dollar", 0.68),
    CNY("Chinese Yuan", 0.13),
    EUR("Euro", 1.0),
    GBP("British Pound Sterling", 1.17),
    JPY("Japanese Yen", 0.008),
    USD("United States Dollar", 0.85);

    private final String description;
    private final Double exchangeRateToEUR;

    CurrencyCode(String description, Double exchangeRateToEUR) {
        this.description = description;
        this.exchangeRateToEUR = exchangeRateToEUR;
    }

}
