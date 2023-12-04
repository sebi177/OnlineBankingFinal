package com.example.onlinebankingfinal.controller;

import com.example.onlinebankingfinal.dto.AccountDTO;
import com.example.onlinebankingfinal.model.Account;
import com.example.onlinebankingfinal.model.enums.AccountStatus;
import com.example.onlinebankingfinal.model.enums.AccountType;
import com.example.onlinebankingfinal.model.enums.CurrencyCode;
import com.example.onlinebankingfinal.service.AccountService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@AutoConfigureMockMvc
@Sql("/delete_tables.sql")
@Sql("/create_tables.sql")
@Sql("/insert_tables.sql")
public class AccountControllerTest {
    @Autowired
    AccountService accountService;

    @Test
    void getAccountDtoById(){
        AccountDTO currentAccount = accountService.getDtoById(UUID.fromString("50991763-ce2c-4862-827f-8cfacbd261e7"));

        AccountDTO expectedAccount = new AccountDTO();
        expectedAccount.setAccountName("Porsche for my Wife");
        expectedAccount.setAccountStatus("ACTIVE");
        expectedAccount.setAccountType("SAVINGS");
        expectedAccount.setAccountCurrencyCode("USD");
        expectedAccount.setAccountBalance("98826.78");
        assertEquals(expectedAccount, currentAccount);
    }

    @Test
    void createAccount(){
        Account thisAccount = new Account();
        thisAccount.setAccountName("Porsche for my Wife");
        thisAccount.setAccountType(AccountType.valueOf("SAVINGS"));
        thisAccount.setAccountStatus(AccountStatus.valueOf("ACTIVE"));
        thisAccount.setAccountBalance(Double.valueOf("98392.36"));
        thisAccount.setAccountCurrencyCode(CurrencyCode.valueOf("EUR"));

        AccountDTO createdDto = accountService.createAccount(thisAccount);

        AccountDTO expectedAccount = new AccountDTO();
        expectedAccount.setAccountName("Porsche for my Wife");
        expectedAccount.setAccountType("SAVINGS");
        expectedAccount.setAccountStatus("ACTIVE");
        expectedAccount.setAccountBalance("98392.36");
        expectedAccount.setAccountCurrencyCode("EUR");
        assertEquals(expectedAccount, createdDto);
    }

    void listAccounts() {
        List<AccountDTO> currentList = accountService.getAllAccounts();

        List<AccountDTO> expectedList = new ArrayList<>();

    }

}
