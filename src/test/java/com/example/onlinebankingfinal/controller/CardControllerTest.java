package com.example.onlinebankingfinal.controller;

import com.example.onlinebankingfinal.dto.CardDTO;
import com.example.onlinebankingfinal.dto.CardFullDTO;
import com.example.onlinebankingfinal.service.CardService;
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

@SpringBootTest
@Sql("/delete_tables.sql")
@Sql("/create_tables.sql")
@Sql("/insert_tables.sql")
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class CardControllerTest {

    @Autowired
    CardService cardService;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void createCard() throws Exception{
        CardDTO expected = new CardDTO();
        expected.setCardType("VISA");
        expected.setCardNumber("8765432112345678");
        expected.setCardHolder("Zhanna Jacot");
        expected.setCvv("234");

        CardFullDTO card = new CardFullDTO();
        card.setCardType("VISA");
        card.setCardNumber("8765432112345678");
        card.setCardHolder("Zhanna Jacot");
        card.setCvv("234");
        card.setClient("a4283a17-e794-4f7f-bf74-0ce24f02bf92");
        card.setAccount("50991763-ce2c-4862-827f-8cfacbd261e7");

        String json = objectMapper.writeValueAsString(card);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/card/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andReturn();

        assertEquals(200, mvcResult.getResponse().getStatus());

        CardDTO createdCard = objectMapper.readValue(mvcResult.getResponse().getContentAsString(),  new TypeReference<>() {
        });

        assertEquals(createdCard, expected);
    }

    @Test
    void getCardById() throws Exception{
        CardDTO expectedCard = new CardDTO();
        expectedCard.setCardType("MASTERCARD");
        expectedCard.setCardNumber("1234567890123456");
        expectedCard.setCardHolder("Alice Johnson");
        expectedCard.setCvv("123");

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/card/find/e854afdd-6db7-4475-8350-f98ed3ab4f7f"))
                .andReturn();

        assertEquals(200, mvcResult.getResponse().getStatus());

        CardDTO returnedCard = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), new TypeReference<>() {
        });
        assertEquals(returnedCard, expectedCard);
    }

    @Test
    void getAllCards() throws Exception{
        CardDTO firstCard = new CardDTO();
        firstCard.setCardType("MASTERCARD");
        firstCard.setCardNumber("1234567890123456");
        firstCard.setCardHolder("Alice Johnson");
        firstCard.setCvv("123");

        CardDTO secondCard = new CardDTO();
        secondCard.setCardType("VISA");
        secondCard.setCardNumber("9876543210987654");
        secondCard.setCardHolder("Bob Smith");
        secondCard.setCvv("456");

        List<CardDTO> expectedList = new ArrayList<>();
        expectedList.add(firstCard);
        expectedList.add(secondCard);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/card/findAll"))
                .andReturn();

        assertEquals(200, mvcResult.getResponse().getStatus());

        List<CardDTO> returnedList = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), new TypeReference<>() {
        });

        assertEquals(returnedList, expectedList);
    }

    @Test
    void updateCard() throws Exception {
        CardDTO cardDTO = new CardDTO();
        cardDTO.setCardType("VISA");
        cardDTO.setCardNumber("8765432112345678");
        cardDTO.setCardHolder("Zhanna Jacot");
        cardDTO.setCvv("234");

        String json = objectMapper.writeValueAsString(cardDTO);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/card/update/e854afdd-6db7-4475-8350-f98ed3ab4f7f")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andReturn();

        assertEquals(200, mvcResult.getResponse().getStatus());

        CardDTO updatedCard = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), new TypeReference<>() {
        });

        assertEquals(updatedCard, cardDTO);
    }

    @Test
    void deleteCard() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.delete("/card/delete/e854afdd-6db7-4475-8350-f98ed3ab4f7f"))
                .andReturn();

        assertEquals(200, mvcResult.getResponse().getStatus());
    }

    @Test
    void getCardByCardNumber() throws Exception{
        CardFullDTO expectedCard = new CardFullDTO();
        expectedCard.setCardType("MASTERCARD");
        expectedCard.setCardNumber("1234567890123456");
        expectedCard.setCardHolder("Alice Johnson");
        expectedCard.setCvv("123");
        expectedCard.setClient("a4283a17-e794-4f7f-bf74-0ce24f02bf92");
        expectedCard.setAccount("50991763-ce2c-4862-827f-8cfacbd261e7");

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/card/1234567890123456"))
                .andReturn();

        assertEquals(200, mvcResult.getResponse().getStatus());

        CardFullDTO returnedCard = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), new TypeReference<>() {
        });

        assertEquals(returnedCard, expectedCard);
    }
}
