package com.example.onlinebankingfinal.service;

import com.example.onlinebankingfinal.dto.CardDTO;
import com.example.onlinebankingfinal.model.Card;

import java.util.List;
import java.util.UUID;

public interface CardService {
    CardDTO createCard(Card card);

    CardDTO getDtoById(UUID cardId);

    Card getById(UUID cardId);

    List<CardDTO> getAllCards();

    CardDTO updateCard(UUID cardId, CardDTO cardDTO);

    void deleteCard(UUID cardId);
}
