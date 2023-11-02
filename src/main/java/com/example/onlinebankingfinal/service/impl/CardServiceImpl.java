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
    public CardDTO createCard(Card card){
        return cardMapper.toDto(cardRepository.save(card));
    }

    @Override
    public CardDTO getDtoById(UUID cardId){
        return cardMapper.toDto(getById(cardId));
    }

    @Override
    public Card getById(UUID cardId){
        return cardRepository.findById(cardId)
                .orElseThrow(() -> new EntityNotFoundException("Card not found!"));
    }

    @Override
    public List<CardDTO> getAllCards(){
        return cardMapper.listToDto(cardRepository.findAll());
    }

    @Override
    public CardDTO updateCard(UUID cardId, CardDTO cardDTO){
        Card existingCard = getById(cardId);
        cardMapper.updateCardFromDTO(cardDTO, existingCard);
        return cardMapper.toDto(cardRepository.save(existingCard));
    }

    @Override
    public void deleteCard(UUID cardId){
        Card existingCard = cardRepository.findById(cardId)
                .orElseThrow(() -> new EntityNotFoundException("Card not found!"));
        cardRepository.delete(existingCard);
    }
}
