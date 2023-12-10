package com.example.onlinebankingfinal.controller;

import com.example.onlinebankingfinal.dto.AccountDTO;
import com.example.onlinebankingfinal.dto.AccountFullDTO;
import com.example.onlinebankingfinal.dto.ClientDTO;
import com.example.onlinebankingfinal.model.Account;
import com.example.onlinebankingfinal.model.enums.AccountStatus;
import com.example.onlinebankingfinal.model.enums.AccountType;
import com.example.onlinebankingfinal.model.enums.CurrencyCode;
import com.example.onlinebankingfinal.service.ClientService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
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
public class ClientControllerTest {

    @Autowired
    ClientService clientService;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void create() throws Exception {
        ClientDTO expectedClient = new ClientDTO();
        expectedClient.setClientStatus("ACTIVE");
        expectedClient.setFirstName("Mihai");
        expectedClient.setLastName("Melnic");
        expectedClient.setPhone("1234567890123");
        expectedClient.setAddress("Main Str. 2");
        expectedClient.setTaxCode("123456789");
        expectedClient.setEmail("mihaimelnic@mail.com");

        String json = objectMapper.writeValueAsString(expectedClient);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/client/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andReturn();
        assertEquals(200, mvcResult.getResponse().getStatus());

        ClientDTO returnedClient = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), new TypeReference<>() {
        });

        assertEquals(returnedClient, expectedClient);
    }

    @Test
    void getClientById() throws Exception {
        ClientDTO expectedClient = new ClientDTO();
        expectedClient.setClientStatus("ACTIVE");
        expectedClient.setFirstName("Alice");
        expectedClient.setLastName("Johnson");
        expectedClient.setPhone("1234567890");
        expectedClient.setAddress("123 Main St");
        expectedClient.setTaxCode("123456789");
        expectedClient.setEmail("alice@example.com");

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/client/find/a4283a17-e794-4f7f-bf74-0ce24f02bf92"))
                .andReturn();

        assertEquals(200, mvcResult.getResponse().getStatus());

        ClientDTO returnedClient = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), new TypeReference<>() {
        });

        assertEquals(returnedClient, expectedClient);
    }

    @Test
    void getAllClients() throws Exception{
        ClientDTO firstClient = new ClientDTO();
        firstClient.setClientStatus("ACTIVE");
        firstClient.setFirstName("Alice");
        firstClient.setLastName("Johnson");
        firstClient.setPhone("1234567890");
        firstClient.setAddress("123 Main St");
        firstClient.setTaxCode("123456789");
        firstClient.setEmail("alice@example.com");

        ClientDTO secondClient = new ClientDTO();
        secondClient.setClientStatus("ACTIVE");
        secondClient.setFirstName("Bob");
        secondClient.setLastName("Smith");
        secondClient.setPhone("9876543210");
        secondClient.setAddress("456 Oak St");
        secondClient.setTaxCode("987654321");
        secondClient.setEmail("bob@example.com");

        List<ClientDTO> expectedList = new ArrayList<>();
        expectedList.add(firstClient);
        expectedList.add(secondClient);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/client/findAll"))
                .andReturn();

        assertEquals(200, mvcResult.getResponse().getStatus());

        List<ClientDTO> returnedList = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), new TypeReference<>() {
        });

        assertEquals(returnedList, expectedList);
    }

    @Test
    void updateClient() throws Exception {
        ClientDTO expected = new ClientDTO();
        expected.setClientStatus("INACTIVE");
        expected.setFirstName("John");
        expected.setLastName("Doe");
        expected.setPhone("9876543210");
        expected.setAddress("789 Elm St");
        expected.setTaxCode("987654321");
        expected.setEmail("john@example.com");

        String json = objectMapper.writeValueAsString(expected);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/client/update/a4283a17-e794-4f7f-bf74-0ce24f02bf92")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andReturn();
        assertEquals(200, mvcResult.getResponse().getStatus());

        ClientDTO returnedClient = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), new TypeReference<>() {
        });

        assertEquals(returnedClient, expected);
    }

    @Test
    void deleteClint() throws Exception{
        ClientDTO expectedClient = new ClientDTO();
        expectedClient.setClientStatus("DELETED");
        expectedClient.setFirstName("Alice");
        expectedClient.setLastName("Johnson");
        expectedClient.setPhone("1234567890");
        expectedClient.setAddress("123 Main St");
        expectedClient.setTaxCode("123456789");
        expectedClient.setEmail("alice@example.com");

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/client/delete/a4283a17-e794-4f7f-bf74-0ce24f02bf92"))
                .andReturn();

        assertEquals(200, mvcResult.getResponse().getStatus());

        ClientDTO returnedClient = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), new TypeReference<>() {
        });
        assertEquals(returnedClient, expectedClient);
    }

    @Test
    void createAccountByClient() throws Exception{
        Account create = new Account();
        create.setAccountStatus(AccountStatus.ACTIVE);
        create.setAccountType(AccountType.CURRENT);
        create.setAccountName("Create Account");
        create.setAccountBalance(Double.valueOf("12345.0"));
        create.setAccountCurrencyCode(CurrencyCode.EUR);

        String json = objectMapper.writeValueAsString(create);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/client/a4283a17-e794-4f7f-bf74-0ce24f02bf92/createAccount")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andReturn();
        assertEquals(200, mvcResult.getResponse().getStatus());

        AccountFullDTO returnedAccount = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), new TypeReference<>() {
        });

        AccountFullDTO expected = new AccountFullDTO();
        expected.setAccountStatus("ACTIVE");
        expected.setAccountType("CURRENT");
        expected.setAccountName("Create Account");
        expected.setAccountBalance("12345.0");
        expected.setAccountCurrencyCode("EUR");
        expected.setClient("a4283a17-e794-4f7f-bf74-0ce24f02bf92");

        assertEquals(returnedAccount, expected);
    }
}
