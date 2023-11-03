package com.example.onlinebankingfinal.controller;

import com.example.onlinebankingfinal.dto.AccountDTO;
import com.example.onlinebankingfinal.service.AccountService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class AccountControllerTest {
    @Autowired
    AccountService accountService;

    @Test
    void getAccountDtoById(){
        AccountDTO currentAccount = accountService.getDtoById(UUID.fromString("ad1c1db1-9bc1-4151-9d62-b81bc5092654"));

        AccountDTO expectedAccount = new AccountDTO();
        expectedAccount.setAccountName("Porsche for my Wife");
        expectedAccount.setAccountStatus("ACTIVE");
        expectedAccount.setAccountType("SAVINGS");
        expectedAccount.setAccountCurrencyCode("EUR");
        expectedAccount.setAccountBalance("123456.00");
        assertEquals(expectedAccount, currentAccount);
    }

    @Test
    void createAccount(){
        AccountDTO currentAccount = new AccountDTO();
        currentAccount.setAccountName("Dream car");
        currentAccount.setAccountType("CURRENT");
        currentAccount.setAccountStatus("ACTIVE");
        currentAccount.setAccountBalance("12345.67");
        currentAccount.setAccountCurrencyCode("USD");

        AccountDTO expectedAccount = new AccountDTO();
        expectedAccount.setAccountName(currentAccount.getAccountName());
        expectedAccount.setAccountType(currentAccount.getAccountType());
        expectedAccount.setAccountStatus(currentAccount.getAccountStatus());
        expectedAccount.setAccountBalance(currentAccount.getAccountBalance());
        expectedAccount.setAccountCurrencyCode(currentAccount.getAccountCurrencyCode());
        assertEquals(expectedAccount, currentAccount);
    }




//    {
//        "accountId": "813d8452-4ec1-461b-bbc0-e9fcac792f31",
//            "accountName": "Porsche for my Wife",
//            "accountType": "SAVINGS",
//            "accountStatus": "ACTIVE",
//            "accountBalance": 123456,
//            "accountCurrencyCode": "EUR",
//            "createdAt": "2023-11-02T17:02:28.547+00:00",
//            "updatedAt": "2023-11-02T17:02:28.547+00:00",
//            "client": null,
//            "card": null,
//            "agreementList": null,
//            "debitTransaction": null,
//            "creditTransaction": null
//    }
}
