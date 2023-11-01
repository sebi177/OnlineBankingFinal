package com.example.onlinebankingfinal.service.impl;

import com.example.onlinebankingfinal.dto.CardDTO;
import com.example.onlinebankingfinal.mapper.CardMapper;
import com.example.onlinebankingfinal.model.Card;
import com.example.onlinebankingfinal.repository.CardRepository;
import com.example.onlinebankingfinal.service.CardService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CardServiceImpl implements CardService {
    private final CardRepository cardRepository;
    private final CardMapper cardMapper;

    @Override
    public Card createCard(Card card){
        return cardRepository.save(card);
    }

    @Override
    public Card getById(UUID cardId){
        return cardRepository.findById(cardId)
                .orElseThrow(() -> new EntityNotFoundException("Card not found!"));
    }

    @Override
    public List<Card> getAllCards(){
        return cardRepository.findAll();
    }

    @Override
    public Card updateCard(UUID cardId, CardDTO cardDTO){
        Card existingCard = getById(cardId);
        cardMapper.updateCardFromDTO(cardDTO, existingCard);
        return cardRepository.save(existingCard);
    }

    @Override
    public void deleteCard(UUID cardId){
        Card existingCard = cardRepository.findById(cardId)
                .orElseThrow(() -> new EntityNotFoundException("Card not found!"));
        cardRepository.delete(existingCard);
    }
}
