package com.example.onlinebankingfinal.controller;

import com.example.onlinebankingfinal.dto.*;
import com.example.onlinebankingfinal.model.Card;
import com.example.onlinebankingfinal.model.enums.CardType;
import com.example.onlinebankingfinal.service.AccountService;
import com.example.onlinebankingfinal.service.exception.AccountNotFoundException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@AutoConfigureMockMvc
@SpringBootTest
@Sql("/delete_tables.sql")
@Sql("/create_tables.sql")
@Sql("/insert_tables.sql")
@ActiveProfiles("test")
public class AccountControllerTest {

    @Autowired
    AccountService accountService;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void createAccount() throws Exception {

        AccountDTO thisAccount = new AccountDTO();
        thisAccount.setAccountName("Porsche for my Wife");
        thisAccount.setAccountType("SAVINGS");
        thisAccount.setAccountStatus("ACTIVE");
        thisAccount.setAccountBalance("98392.36");
        thisAccount.setAccountCurrencyCode("EUR");

        String createdDto = objectMapper.writeValueAsString(thisAccount);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/account/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(createdDto))
                .andReturn();

        assertEquals(200, mvcResult.getResponse().getStatus());

        AccountDTO currentAccount = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), new TypeReference<>() {
        });

        assertEquals(thisAccount, currentAccount);
    }

    @Test
    void getAccountById() throws Exception {

        AccountDTO expectedAccount = new AccountDTO();
        expectedAccount.setAccountName("Porsche for my Wife");
        expectedAccount.setAccountStatus("ACTIVE");
        expectedAccount.setAccountType("SAVINGS");
        expectedAccount.setAccountCurrencyCode("USD");
        expectedAccount.setAccountBalance("98826.78");

        MvcResult mvcResult = mockMvc.perform(
                        MockMvcRequestBuilders.get("/account/find/{accountId}", "50991763-ce2c-4862-827f-8cfacbd261e7"))
                .andReturn();

        assertEquals(200, mvcResult.getResponse().getStatus());

        AccountDTO currentAccount = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), new TypeReference<>() {
        });

        assertEquals(expectedAccount, currentAccount);
    }

    @Test
    void getAccountByIdNotFound() throws Exception {

        MvcResult mvcResult = mockMvc.perform(
                        MockMvcRequestBuilders.get("/account/find/1234"))
                .andReturn();

        assertEquals(500, mvcResult.getResponse().getStatus());
    }

    @Test
    void getAllAccounts() throws Exception {

        AccountDTO firstAccount = new AccountDTO();
        firstAccount.setAccountName("Porsche for my Wife");
        firstAccount.setAccountType("SAVINGS");
        firstAccount.setAccountStatus("ACTIVE");
        firstAccount.setAccountBalance("98826.78");
        firstAccount.setAccountCurrencyCode("USD");

        AccountDTO secondAccount = new AccountDTO();
        secondAccount.setAccountName("Alfa Romeo for my Husband");
        secondAccount.setAccountType("CURRENT");
        secondAccount.setAccountStatus("ACTIVE");
        secondAccount.setAccountBalance("142982.33");
        secondAccount.setAccountCurrencyCode("EUR");

        List<AccountDTO> currentList = new ArrayList<>();
        currentList.add(firstAccount);
        currentList.add(secondAccount);

        MvcResult mvcResult = mockMvc.perform(
                        MockMvcRequestBuilders.get("/account/findAll"))
                .andReturn();

        assertEquals(200, mvcResult.getResponse().getStatus());

        List<AccountDTO> accountList = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), new TypeReference<>() {
        });

        assertEquals(currentList, accountList);
    }


    @Test
    void updateAccount() throws Exception {
        AccountDTO updatedAccount = new AccountDTO();
        updatedAccount.setAccountName("Updated Account");
        updatedAccount.setAccountType("SAVINGS");
        updatedAccount.setAccountStatus("ACTIVE");
        updatedAccount.setAccountBalance("100000.0");
        updatedAccount.setAccountCurrencyCode("USD");

        String updatedDto = objectMapper.writeValueAsString(updatedAccount);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/account/update/50991763-ce2c-4862-827f-8cfacbd261e7")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updatedDto))
                .andReturn();

        assertEquals(200, mvcResult.getResponse().getStatus());

        AccountDTO currentAccount = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), new TypeReference<>() {
        });

        assertEquals(updatedAccount, currentAccount);
    }

    @Test
    void getTransactions() throws Exception {

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/account/transactions/50991763-ce2c-4862-827f-8cfacbd261e7"))
                .andReturn();

        assertEquals(200, mvcResult.getResponse().getStatus());

        TransactionDTO firstTransaction = new TransactionDTO();
        firstTransaction.setTransactionType("TRANSFER");
        firstTransaction.setTransactionAmount("5000.0");
        firstTransaction.setTransactionCurrencyCode("USD");
        firstTransaction.setTransactionDescription("Purchase");

        TransactionDTO secondTransaction = new TransactionDTO();
        secondTransaction.setTransactionType("REFUND");
        secondTransaction.setTransactionAmount("3000.0");
        secondTransaction.setTransactionCurrencyCode("EUR");
        secondTransaction.setTransactionDescription("Refund");

        List<TransactionDTO> transactionDTOList = new ArrayList<>();
        transactionDTOList.add(firstTransaction);
        transactionDTOList.add(secondTransaction);

        List<TransactionDTO> currentList = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), new TypeReference<>() {
        });

        assertEquals(transactionDTOList, currentList);
    }

    @Test
    void createCardByAccount() throws Exception {
        Card thisCard = new Card();
        thisCard.setCardType(CardType.valueOf("MASTERCARD"));
        thisCard.setCardNumber("1234567890123456");
        thisCard.setCardHolder("Alice Johnson");
        thisCard.setCvv(Integer.valueOf("444"));

        String json = objectMapper.writeValueAsString(thisCard);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/account/50991763-ce2c-4862-827f-8cfacbd261e7/createCard")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andReturn();

        assertEquals(200, mvcResult.getResponse().getStatus());

        CardDTO currentCard = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), new TypeReference<>() {
        });

        CardDTO expectedCard = new CardDTO();
        expectedCard.setCardType("MASTERCARD");
        expectedCard.setCardNumber("1234567890123456");
        expectedCard.setCardHolder("Alice Johnson");
        expectedCard.setCvv("444");
        assertEquals(expectedCard, currentCard);
    }

    @Test
    void createAgreementByAccount() throws Exception {

        AgreementFullDTO thisAgreement = new AgreementFullDTO();
        thisAgreement.setAgreementStatus("ACTIVE");
        thisAgreement.setAgreementSum("50000");
        thisAgreement.setInterestRate("0.05");
        thisAgreement.setProduct("7cb780c5-7b00-4e89-b8cf-0af4fc4a1a13");
        thisAgreement.setAccount("50991763-ce2c-4862-827f-8cfacbd261e7");
        String json = objectMapper.writeValueAsString(thisAgreement);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/account/50991763-ce2c-4862-827f-8cfacbd261e7/createAgreement")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andReturn();

        assertEquals(200, mvcResult.getResponse().getStatus());

        AgreementFullDTO currentAgreement = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), new TypeReference<>() {
        });

        AgreementFullDTO expectedAgreement = new AgreementFullDTO();
        expectedAgreement.setAgreementStatus("ACTIVE");
        expectedAgreement.setAgreementSum("50000");
        expectedAgreement.setInterestRate("0.05");
        expectedAgreement.setProduct("7cb780c5-7b00-4e89-b8cf-0af4fc4a1a13");
        expectedAgreement.setAccount("50991763-ce2c-4862-827f-8cfacbd261e7");
        assertEquals(expectedAgreement, currentAgreement);
    }

    @Test
    void getAccountByCardNumber() throws Exception {
        MvcResult mvcResult = mockMvc.perform(
                        MockMvcRequestBuilders.get("/account/1234567890123456"))
                .andReturn();

        assertEquals(200, mvcResult.getResponse().getStatus());

        AccountFullDTO currentAccount = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), new TypeReference<>() {
        });

        AccountFullDTO expectedAccount = new AccountFullDTO();
        expectedAccount.setAccountName("Porsche for my Wife");
        expectedAccount.setAccountStatus("ACTIVE");
        expectedAccount.setAccountType("SAVINGS");
        expectedAccount.setAccountCurrencyCode("USD");
        expectedAccount.setAccountBalance("98826.78");
        expectedAccount.setClient("a4283a17-e794-4f7f-bf74-0ce24f02bf92");

        assertEquals(expectedAccount, currentAccount);
    }

    @Test
    void statistic() throws Exception {

        MvcResult mvcResult = mockMvc.perform(
                        MockMvcRequestBuilders.get("/account/statistic/50991763-ce2c-4862-827f-8cfacbd261e7"))
                .andReturn();

        assertEquals(200, mvcResult.getResponse().getStatus());
        TransactionStatisticsDTO expectedStatistic = new TransactionStatisticsDTO(Double.parseDouble("3000.0"), Double.parseDouble("5000.0"), Double.parseDouble("37.5"), Double.parseDouble("62.5"));

        TransactionStatisticsDTO currentStatistic = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), new TypeReference<>() {
        });

        assertEquals(expectedStatistic, currentStatistic);
    }

    @Test
    void delete() throws Exception {
        AccountDTO updatedAccount = new AccountDTO();
        updatedAccount.setAccountName("Porsche for my Wife");
        updatedAccount.setAccountType("SAVINGS");
        updatedAccount.setAccountStatus("DELETED");
        updatedAccount.setAccountBalance("98826.78");
        updatedAccount.setAccountCurrencyCode("USD");

        String updatedDto = objectMapper.writeValueAsString(updatedAccount);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/account/delete/50991763-ce2c-4862-827f-8cfacbd261e7")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updatedDto))
                .andReturn();

        assertEquals(200, mvcResult.getResponse().getStatus());

        AccountDTO currentAccount = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), new TypeReference<>() {
        });

        assertEquals(updatedAccount, currentAccount);
    }

}
