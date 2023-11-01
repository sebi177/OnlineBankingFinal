package com.example.onlinebankingfinal.service;

import com.example.onlinebankingfinal.dto.CardDTO;
import com.example.onlinebankingfinal.model.Card;

import java.util.List;
import java.util.UUID;

public interface CardService {
    Card createCard(Card card);

    Card getById(UUID cardId);

    List<Card> getAllCards();

    Card updateCard(UUID cardId, CardDTO cardDTO);

    void deleteCard(UUID cardId);
}
