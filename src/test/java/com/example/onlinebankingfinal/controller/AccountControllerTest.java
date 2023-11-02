package com.example.onlinebankingfinal.controller;

import com.example.onlinebankingfinal.dto.AccountDTO;
import com.example.onlinebankingfinal.model.Account;
import com.example.onlinebankingfinal.service.AccountService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class AccountControllerTest {

    private AccountController accountController;
    private AccountService accountService;
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        accountService = Mockito.mock(AccountService.class);
        accountController = new AccountController(accountService);
        mockMvc = MockMvcBuilders.standaloneSetup(accountController).build();
    }

    @Test
    public void testCreateAccount() throws Exception {
        Account account = new Account(); // Creați un obiect Account de test
        when(accountService.createAccount(any(Account.class))).thenReturn(account);

        mockMvc.perform(post("/account/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(account)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.accountName").value(account.getAccountName()))
                .andExpect(jsonPath("$.accountBalance").value(account.getAccountBalance()));
    }

    @Test
    public void testGetAccountById() throws Exception {
        UUID accountId = UUID.randomUUID();
        Account account = new Account();
        when(accountService.getById(accountId)).thenReturn(account);

        mockMvc.perform(get("/account/find/{accountId}", accountId.toString()))
                .andExpect(status().isFound())
                .andExpect(jsonPath("$.accountName").value(account.getAccountName()))
                .andExpect(jsonPath("$.accountBalance").value(account.getAccountBalance()));
    }

    @Test
    public void testGetAllAccounts() throws Exception {
        Account account = new Account();
        when(accountService.getAllAccounts()).thenReturn(Collections.singletonList(account));

        mockMvc.perform(get("/account/findAll"))
                .andExpect(status().isFound())
                .andExpect(jsonPath("$[0].accountName").value(account.getAccountName()))
                .andExpect(jsonPath("$[0].accountBalance").value(account.getAccountBalance()));
    }

    @Test
    public void testUpdateAccount() throws Exception {
        UUID accountId = UUID.randomUUID();
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setAccountName("New Account Name");
        accountDTO.setAccountBalance("1000.0");

        Account updatedAccount = new Account();
        updatedAccount.setAccountName("New Account Name");
        updatedAccount.setAccountBalance(BigDecimal.valueOf(1000.0));

        Mockito.when(accountService.updateAccount(accountId, accountDTO)).thenReturn(updatedAccount);

        mockMvc.perform(MockMvcRequestBuilders.post("/account/update/{accountId}", accountId.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(accountDTO)))
                .andExpect(MockMvcResultMatchers.status().isAccepted());
//                .andExpect(MockMvcResultMatchers.jsonPath("$.accountName").value("New Account Name"));
//                .andExpect(MockMvcResultMatchers.jsonPath("$.accountBalance").value(1000.0));
    }


    @Test
    public void testDeleteAccount() throws Exception {
        UUID accountId = UUID.randomUUID();

        mockMvc.perform(delete("/account/delete/{accountId}", accountId.toString()))
                .andExpect(status().isOk());

        // Verificăm că serviciul a fost apelat cu ID-ul corespunzător pentru ștergere
        Mockito.verify(accountService).deleteAccount(accountId);
    }

    // O metodă utilitară pentru a serializa obiectul în JSON
    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
