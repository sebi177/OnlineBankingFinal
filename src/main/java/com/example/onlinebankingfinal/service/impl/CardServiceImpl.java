package com.example.onlinebankingfinal.service.impl;

import com.example.onlinebankingfinal.dto.CardDTO;
import com.example.onlinebankingfinal.dto.CardFullDTO;
import com.example.onlinebankingfinal.mapper.CardMapper;
import com.example.onlinebankingfinal.model.Account;
import com.example.onlinebankingfinal.model.Card;
import com.example.onlinebankingfinal.model.Client;
import com.example.onlinebankingfinal.model.enums.CardType;
import com.example.onlinebankingfinal.repository.CardRepository;
import com.example.onlinebankingfinal.service.AccountService;
import com.example.onlinebankingfinal.service.CardService;
import com.example.onlinebankingfinal.service.ClientService;
import com.example.onlinebankingfinal.service.exception.CardNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CardServiceImpl implements CardService {
    private final CardRepository cardRepository;
    private final CardMapper cardMapper;
    private final AccountService accountService;
    private final ClientService clientService;

    @Override
    public CardDTO createCard(Card card) {
        return cardMapper.toDto(cardRepository.save(card));
    }

    @Override
    public CardDTO getDtoById(UUID cardId) {
        return cardMapper.toDto(getById(cardId));
    }

    @Override
    public Card getById(UUID cardId) {
        return cardRepository.findById(cardId)
                .orElseThrow(() -> new CardNotFoundException("Card not found!"));
    }

    @Override
    public List<CardDTO> getAllCards() {
        return cardMapper.listToDto(cardRepository.findAll());
    }

    @Override
    public CardDTO updateCard(UUID cardId, CardDTO cardDTO) {
        Card existingCard = getById(cardId);
        cardMapper.updateCardFromDTO(cardDTO, existingCard);
        return cardMapper.toDto(cardRepository.save(existingCard));
    }

    @Override
    public void deleteCard(UUID cardId) {
        Card existingCard = getById(cardId);
        cardRepository.delete(existingCard);
    }

    @Override
    public CardFullDTO createCardByAccount(UUID accountId, Card card) {
        Account thisAccount = accountService.getById(accountId);
        Client thisClient = thisAccount.getClient();
        card.setAccount(thisAccount);
        card.setClient(thisClient);
        cardRepository.save(card);
        return cardMapper.toFullDto(card);
    }

    @Override
    public CardFullDTO getCardByCardNumber(String cardNumber) {
        Card existingCard = cardRepository.findByCardNumber(cardNumber);
        return cardMapper.toFullDto(existingCard);
    }

}
