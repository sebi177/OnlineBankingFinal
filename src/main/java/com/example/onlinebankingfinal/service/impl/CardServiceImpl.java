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
                .orElseThrow(() -> new EntityNotFoundException("Card not found!"));
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
        Card existingCard = cardRepository.findById(cardId)
                .orElseThrow(() -> new EntityNotFoundException("Card not found!"));
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

    @Override
    public CardFullDTO generateCard(Account account) {
        Client thisClient = clientService.getById(account.getClient().getClientId());
        Card newCard = new Card();
        newCard.setAccount(account);
        newCard.setClient(thisClient);
        newCard.setCardHolder(generateCardHolder(thisClient));
        newCard.setCardType(CardType.VISA);
        newCard.setCardNumber(generateCardNumber(CardType.VISA));
        newCard.setCvv(cvvGenerator());
        cardRepository.save(newCard);
        newCard.setExpirationDate(expirationDate(newCard.getCreatedAt()));
        cardRepository.save(newCard);
        return cardMapper.toFullDto(newCard);
    }

    public String generateCardHolder(Client client) {
        return client.getFirstName() + " " + client.getLastName();
    }

    public String generateCardNumber(CardType cardType) {
        int startingDigit = 0;

        if (cardType == CardType.VISA) {
            startingDigit = 4;
        } else if (cardType == CardType.MASTERCARD){
            startingDigit = 5;
        }

        StringBuilder cardNumber = new StringBuilder(String.valueOf(startingDigit));

        for (int i = 0; i < 14; i++) {
            int randomDigit = new Random().nextInt(10);
            cardNumber.append(randomDigit);
        }

        return cardNumber.toString();
    }

    public LocalDateTime expirationDate(LocalDateTime createdAt) {
        return createdAt.plusYears(3);
    }

    public Integer cvvGenerator() {
        return new Random().nextInt(100, 1000);
    }
}
