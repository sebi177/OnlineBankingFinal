package com.example.onlinebankingfinal.controller;

import com.example.onlinebankingfinal.dto.CardDTO;
import com.example.onlinebankingfinal.model.Card;
import com.example.onlinebankingfinal.service.CardService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class CardControllerTest {

    private CardController cardController;
    private CardService cardService;
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        cardService = Mockito.mock(CardService.class);
        cardController = new CardController(cardService);
        mockMvc = MockMvcBuilders.standaloneSetup(cardController).build();
    }

    @Test
    public void testCreateCard() throws Exception {
        Card card = new Card(); // Creați un obiect Card de test
        when(cardService.createCard(any(Card.class))).thenReturn(card);

        mockMvc.perform(post("/card/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(card)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.cardNumber").value(card.getCardNumber()));
    }

    @Test
    public void testGetCardById() throws Exception {
        UUID cardId = UUID.randomUUID();
        Card card = new Card();
        when(cardService.getById(cardId)).thenReturn(card);

        mockMvc.perform(get("/card/find/{cardId}", cardId.toString()))
                .andExpect(status().isFound())
                .andExpect(jsonPath("$.cardNumber").value(card.getCardNumber()));
    }

    @Test
    public void testGetAllCards() throws Exception {
        Card card = new Card();
        when(cardService.getAllCards()).thenReturn(Collections.singletonList(card));

        mockMvc.perform(get("/card/findAll"))
                .andExpect(status().isFound())
                .andExpect(jsonPath("$[0].cardNumber").value(card.getCardNumber()));
    }

    @Test
    public void testUpdateCard() throws Exception {
        UUID cardId = UUID.randomUUID();
        CardDTO cardDTO = new CardDTO();
        cardDTO.setCardNumber("1234567890123456");

        Card updatedCard = new Card();
        updatedCard.setCardNumber("1234567890123456");

        Mockito.when(cardService.updateCard(cardId, cardDTO)).thenReturn(updatedCard);

        mockMvc.perform(MockMvcRequestBuilders.post("/card/update/{cardId}", cardId.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(cardDTO)))
                .andExpect(MockMvcResultMatchers.status().isAccepted());
    }

    @Test
    public void testDeleteCard() throws Exception {
        UUID cardId = UUID.randomUUID();

        mockMvc.perform(delete("/card/delete/{cardId}", cardId.toString()))
                .andExpect(status().isOk());

        // Verificăm că serviciul a fost apelat cu ID-ul corespunzător pentru ștergere
        Mockito.verify(cardService).deleteCard(cardId);
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

