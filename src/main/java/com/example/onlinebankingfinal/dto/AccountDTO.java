package com.example.onlinebankingfinal.dto;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//@Getter
//@Setter
//@NoArgsConstructor
@Data
public class AccountDTO {
    private String accountName;
    private String accountType;
    private String accountStatus;
    private String accountBalance;
    private String accountCurrencyCode;
}
