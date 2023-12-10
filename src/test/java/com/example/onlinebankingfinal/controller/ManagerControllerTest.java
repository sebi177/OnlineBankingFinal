package com.example.onlinebankingfinal.controller;

import com.example.onlinebankingfinal.dto.ClientFullDTO;
import com.example.onlinebankingfinal.dto.ManagerDTO;
import com.example.onlinebankingfinal.dto.ProductFullDTO;
import com.example.onlinebankingfinal.model.Client;
import com.example.onlinebankingfinal.model.Product;
import com.example.onlinebankingfinal.model.enums.ClientStatus;
import com.example.onlinebankingfinal.model.enums.CurrencyCode;
import com.example.onlinebankingfinal.model.enums.ProductStatus;
import com.example.onlinebankingfinal.service.ManagerService;
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
public class ManagerControllerTest {

    @Autowired
    ManagerService managerService;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void createManager() throws Exception{
        ManagerDTO create = new ManagerDTO();
        create.setManagerStatus("ACTIVE");
        create.setFirstName("Ion");
        create.setLastName("Cucos");

        String json = objectMapper.writeValueAsString(create);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/manager/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andReturn();
        assertEquals(200, mvcResult.getResponse().getStatus());

        ManagerDTO returned = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), new TypeReference<>() {
        });

        assertEquals(returned, create);
    }

    @Test
    void getById() throws Exception {
        ManagerDTO expected = new ManagerDTO();
        expected.setFirstName("John");
        expected.setLastName("Doe");
        expected.setManagerStatus("ACTIVE");

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/manager/find/82d07ab4-7319-4ac0-af54-167663454b48"))
                .andReturn();

        assertEquals(200, mvcResult.getResponse().getStatus());

        ManagerDTO returned = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), new TypeReference<>() {
        });

        assertEquals(returned, expected);
    }

    @Test
    void getAllManagers() throws Exception{
        ManagerDTO first = new ManagerDTO();
        first.setFirstName("John");
        first.setLastName("Doe");
        first.setManagerStatus("ACTIVE");

        ManagerDTO second = new ManagerDTO();
        second.setFirstName("Jane");
        second.setLastName("Smith");
        second.setManagerStatus("ACTIVE");

        List<ManagerDTO> expected = new ArrayList<>();
        expected.add(first);
        expected.add(second);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/manager/findAll"))
                .andReturn();

        assertEquals(200, mvcResult.getResponse().getStatus());

        List<ManagerDTO> returned = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), new TypeReference<>() {
        });
        assertEquals(expected, returned);
    }

    @Test
    void updateManager() throws Exception {
        ManagerDTO update = new ManagerDTO();
        update.setFirstName("Updated");
        update.setLastName("Manager");
        update.setManagerStatus("ACTIVE");

        String json = objectMapper.writeValueAsString(update);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/manager/update/82d07ab4-7319-4ac0-af54-167663454b48")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andReturn();

        assertEquals(200, mvcResult.getResponse().getStatus());

        ManagerDTO returned = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), new TypeReference<>() {
        });

        assertEquals(returned, update);
    }

    @Test
    void deleteManager() throws Exception{
        ManagerDTO expected = new ManagerDTO();
        expected.setFirstName("John");
        expected.setLastName("Doe");
        expected.setManagerStatus("DELETED");

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/manager/delete/82d07ab4-7319-4ac0-af54-167663454b48"))
                .andReturn();

        assertEquals(200, mvcResult.getResponse().getStatus());

        ManagerDTO returned = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), new TypeReference<>() {
        });

        assertEquals(returned, expected);
    }

    @Test
    void createClientByManager() throws Exception{
        Client create = new Client();
        create.setClientStatus(ClientStatus.ACTIVE);
        create.setFirstName("Mihai");
        create.setLastName("Melnic");
        create.setPhone("1234567890123");
        create.setAddress("Main Str. 2");
        create.setTaxCode("123456789");
        create.setEmail("mihaimelnic@mail.com");

        String json = objectMapper.writeValueAsString(create);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/manager/82d07ab4-7319-4ac0-af54-167663454b48/createClient")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andReturn();
        assertEquals(200, mvcResult.getResponse().getStatus());

        ClientFullDTO expected = new ClientFullDTO();
        expected.setClientStatus("ACTIVE");
        expected.setFirstName("Mihai");
        expected.setLastName("Melnic");
        expected.setPhone("1234567890123");
        expected.setAddress("Main Str. 2");
        expected.setTaxCode("123456789");
        expected.setEmail("mihaimelnic@mail.com");
        expected.setManager("82d07ab4-7319-4ac0-af54-167663454b48");

        ClientFullDTO returned = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), new TypeReference<>() {
        });

        assertEquals(returned, expected);
    }

    @Test
    void createProduct() throws Exception{
        Product create = new Product();
        create.setInterestRate(Double.valueOf("0.5"));
        create.setProductLimit(Integer.valueOf("10000"));
        create.setProductCurrencyCode(CurrencyCode.EUR);
        create.setProductStatus(ProductStatus.AVAILABLE);
        create.setProductName("Created");

        String json = objectMapper.writeValueAsString(create);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/manager/82d07ab4-7319-4ac0-af54-167663454b48/createProduct")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andReturn();
        assertEquals(200, mvcResult.getResponse().getStatus());

        ProductFullDTO expected = new ProductFullDTO();
        expected.setInterestRate("0.5");
        expected.setProductLimit("10000");
        expected.setProductCurrencyCode("EUR");
        expected.setProductStatus("AVAILABLE");
        expected.setProductName("Created");
        expected.setManager("82d07ab4-7319-4ac0-af54-167663454b48");

        ProductFullDTO returned = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), new TypeReference<>() {
        });

        assertEquals(returned, expected);
    }

}
