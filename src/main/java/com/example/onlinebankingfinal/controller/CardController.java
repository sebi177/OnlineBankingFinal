package com.example.onlinebankingfinal.controller;

import com.example.onlinebankingfinal.dto.CardDTO;
import com.example.onlinebankingfinal.dto.CardFullDTO;
import com.example.onlinebankingfinal.model.Card;
import com.example.onlinebankingfinal.service.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/card")
public class CardController {
    private final CardService cardService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.OK)
    public CardDTO createCard(@RequestBody CardFullDTO card) {
        return cardService.createCard(card);
    }

    @GetMapping("/find/{cardId}")
    @ResponseStatus(HttpStatus.OK)
    public CardDTO getCardById(@PathVariable UUID cardId) {
        return cardService.getDtoById(cardId);
    }

    @GetMapping("/findAll")
    @ResponseStatus(HttpStatus.OK)
    public List<CardDTO> getAllCards(){
        return cardService.getAllCards();
    }

    @PostMapping("/update/{cardId}")
    @ResponseStatus(HttpStatus.OK)
    public CardDTO updateCard(@PathVariable UUID cardId, @RequestBody CardDTO cardDTO){
        return cardService.updateCard(cardId, cardDTO);
    }

    @DeleteMapping("/delete/{cardId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteCard(@PathVariable UUID cardId){
        cardService.deleteCard(cardId);
    }

    @GetMapping("/{cardNumber}")
    @ResponseStatus(HttpStatus.OK)
    public CardFullDTO findCardByCardNumber(@PathVariable String cardNumber){
        return cardService.getCardByCardNumber(cardNumber);
    }
}
