package com.example.onlinebankingfinal.controller;

import com.example.onlinebankingfinal.dto.ProductDTO;
import com.example.onlinebankingfinal.dto.ProductFullDTO;
import com.example.onlinebankingfinal.model.Product;
import com.example.onlinebankingfinal.model.enums.CurrencyCode;
import com.example.onlinebankingfinal.model.enums.ProductStatus;
import com.example.onlinebankingfinal.service.ProductService;
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
public class ProductControllerTest {

    @Autowired
    ProductService productService;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void createProduct() throws Exception{
        ProductDTO expected = new ProductDTO();
        expected.setProductName("Test Product");
        expected.setProductStatus("AVAILABLE");
        expected.setProductLimit("1000");
        expected.setInterestRate("5.5");
        expected.setProductCurrencyCode("EUR");

        ProductFullDTO create = new ProductFullDTO();
        create.setProductName("Test Product");
        create.setProductStatus("AVAILABLE");
        create.setProductLimit("1000");
        create.setInterestRate("5.5");
        create.setProductCurrencyCode("EUR");
        create.setManager("82d07ab4-7319-4ac0-af54-167663454b48");

        String json = objectMapper.writeValueAsString(create);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/product/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andReturn();
        assertEquals(200, mvcResult.getResponse().getStatus());

        ProductDTO returned = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), new TypeReference<>() {
        });

        assertEquals(returned, expected);
    }

    @Test
    void getById() throws Exception{

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/product/find/7cb780c5-7b00-4e89-b8cf-0af4fc4a1a13"))
                .andReturn();
        assertEquals(200, mvcResult.getResponse().getStatus());

        ProductDTO expected = new ProductDTO();
        expected.setProductName("Product 1");
        expected.setProductStatus("AVAILABLE");
        expected.setProductLimit("100000");
        expected.setInterestRate("0.05");
        expected.setProductCurrencyCode("USD");

        ProductDTO returned = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), new TypeReference<>() {
        });

        assertEquals(returned, expected);
    }

    @Test
    void getAllProduct() throws Exception{
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/product/findAll"))
                .andReturn();
        assertEquals(200, mvcResult.getResponse().getStatus());

        List<ProductDTO> expected = new ArrayList<>();
        ProductDTO first = new ProductDTO();
        first.setProductName("Product 1");
        first.setProductStatus("AVAILABLE");
        first.setProductLimit("100000");
        first.setInterestRate("0.05");
        first.setProductCurrencyCode("USD");
        expected.add(first);

        ProductDTO second = new ProductDTO();
        second.setProductName("Product 2");
        second.setProductStatus("AVAILABLE");
        second.setProductLimit("50000");
        second.setInterestRate("0.03");
        second.setProductCurrencyCode("EUR");
        expected.add(second);

        List<ProductDTO> returned = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), new TypeReference<>() {
        });

        assertEquals(returned, expected);
    }

    @Test
    void updateProduct() throws Exception{
        ProductDTO expected = new ProductDTO();
        expected.setProductName("Updated Product");
        expected.setProductStatus("AVAILABLE");
        expected.setProductLimit("100000");
        expected.setInterestRate("0.05");
        expected.setProductCurrencyCode("USD");

        String json = objectMapper.writeValueAsString(expected);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/product/update/7cb780c5-7b00-4e89-b8cf-0af4fc4a1a13")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andReturn();
        assertEquals(200, mvcResult.getResponse().getStatus());


        ProductDTO returned = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), new TypeReference<>() {
        });

        assertEquals(returned, expected);
    }

    @Test
    void deleteProduct()throws Exception{
        ProductDTO expected = new ProductDTO();
        expected.setProductName("Product 1");
        expected.setProductStatus("DELETED");
        expected.setProductLimit("100000");
        expected.setInterestRate("0.05");
        expected.setProductCurrencyCode("USD");

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/product/delete/7cb780c5-7b00-4e89-b8cf-0af4fc4a1a13"))
                .andReturn();
        assertEquals(200, mvcResult.getResponse().getStatus());

        ProductDTO returned = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), new TypeReference<>() {
        });
        assertEquals(returned, expected);
    }
}
