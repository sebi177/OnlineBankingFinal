package com.example.onlinebankingfinal.controller;

import com.example.onlinebankingfinal.dto.TransactionDTO;
import com.example.onlinebankingfinal.dto.TransactionFullDTO;
import com.example.onlinebankingfinal.model.Transaction;
import com.example.onlinebankingfinal.model.enums.CurrencyCode;
import com.example.onlinebankingfinal.model.enums.TransactionType;
import com.example.onlinebankingfinal.service.AccountService;
import com.example.onlinebankingfinal.service.TransactionService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@AutoConfigureMockMvc
@SpringBootTest
@Sql("/delete_tables.sql")
@Sql("/create_tables.sql")
@Sql("/insert_tables.sql")
@ActiveProfiles("test")
public class TransactionControllerTest {
    @Autowired
    AccountService accountService;
    @Autowired
    TransactionService transactionService;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void createTransaction() throws Exception {
        Transaction create = new Transaction();
        create.setTransactionType(TransactionType.TRANSFER);
        create.setTransactionAmount(Double.valueOf("100.0"));
        create.setTransactionCurrencyCode(CurrencyCode.EUR);
        create.setTransactionDescription("Test");

        String json = objectMapper.writeValueAsString(create);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/transaction/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andReturn();
        assertEquals(200, mvcResult.getResponse().getStatus());

        TransactionDTO expected = new TransactionDTO();
        expected.setTransactionType("TRANSFER");
        expected.setTransactionAmount("100.0");
        expected.setTransactionCurrencyCode("EUR");
        expected.setTransactionDescription("Test");

        TransactionDTO returned = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), new TypeReference<>() {
        });

        assertEquals(returned, expected);
    }

    @Test
    void getTransactionById() throws Exception{
        TransactionDTO expect = new TransactionDTO();
        expect.setTransactionType("TRANSFER");
        expect.setTransactionAmount("5000.0");
        expect.setTransactionCurrencyCode("USD");
        expect.setTransactionDescription("Purchase");

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/transaction/find/fef3af1f-7dc6-423d-a475-87d46d798e0e"))
                .andReturn();
        assertEquals(200, mvcResult.getResponse().getStatus());

        TransactionDTO returned = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), new TypeReference<>() {
        });
        assertEquals(returned, expect);
    }

    @Test
    void getAllTransactions() throws Exception{
        TransactionDTO first = new TransactionDTO();
        first.setTransactionType("TRANSFER");
        first.setTransactionAmount("5000.0");
        first.setTransactionCurrencyCode("USD");
        first.setTransactionDescription("Purchase");

        TransactionDTO second = new TransactionDTO();
        second.setTransactionType("REFUND");
        second.setTransactionAmount("3000.0");
        second.setTransactionCurrencyCode("EUR");
        second.setTransactionDescription("Refund");

        List<TransactionDTO> expectedList = new ArrayList<>();
        expectedList.add(first);
        expectedList.add(second);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/transaction/findAll"))
                .andReturn();
        assertEquals(200, mvcResult.getResponse().getStatus());

        List<TransactionDTO> returnedList = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), new TypeReference<>() {
        });
        assertEquals(returnedList, expectedList);
    }

    @Test
    void deleteTransaction() throws Exception{
        TransactionDTO expected = new TransactionDTO();
        expected.setTransactionType("DELETED");
        expected.setTransactionAmount("5000.0");
        expected.setTransactionCurrencyCode("USD");
        expected.setTransactionDescription("Purchase");

        String json = objectMapper.writeValueAsString(expected);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/transaction/delete/fef3af1f-7dc6-423d-a475-87d46d798e0e")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andReturn();
        assertEquals(200, mvcResult.getResponse().getStatus());

        TransactionDTO returned = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), new TypeReference<>() {
        });

        assertEquals(returned, expected);
    }

    @Test
    void performTransaction() throws Exception{
        TransactionFullDTO expected = new TransactionFullDTO();
        expected.setTransactionType("TRANSFER");
        expected.setTransactionAmount("100.0");
        expected.setTransactionCurrencyCode("EUR");
        expected.setTransactionDescription("Test");
        expected.setCreditAccount("50991763-ce2c-4862-827f-8cfacbd261e7");
        expected.setDebitAccount("2115a101-bae8-49b1-87b5-1de5265ea492");

        String json = objectMapper.writeValueAsString(expected);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/transaction/transfer")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andReturn();
        assertEquals(200, mvcResult.getResponse().getStatus());

        TransactionFullDTO returned = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), new TypeReference<>() {
        });
        assertEquals(returned, expected);
    }

    @Test
    void performTransactionNegativeBalance() throws Exception{
        TransactionFullDTO expected = new TransactionFullDTO();
        expected.setTransactionType("TRANSFER");
        expected.setTransactionAmount("200000.0");
        expected.setTransactionCurrencyCode("EUR");
        expected.setTransactionDescription("Test");
        expected.setCreditAccount("50991763-ce2c-4862-827f-8cfacbd261e7");
        expected.setDebitAccount("2115a101-bae8-49b1-87b5-1de5265ea492");

        String json = objectMapper.writeValueAsString(expected);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/transaction/transfer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andReturn();
        assertEquals(500, mvcResult.getResponse().getStatus());
    }
}
