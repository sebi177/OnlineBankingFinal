package com.example.onlinebankingfinal.controller;

import com.example.onlinebankingfinal.dto.CardDTO;
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
    public ResponseEntity<CardDTO> createCard(@RequestBody Card card) {
        CardDTO createdCard = cardService.createCard(card);
        return new ResponseEntity<>(createdCard, HttpStatus.CREATED);
    }

    @GetMapping("/find/{cardId}")
    public ResponseEntity<CardDTO> getCardById(@PathVariable UUID cardId) {
        CardDTO foundedCard = cardService.getDtoById(cardId);
        return new ResponseEntity<>(foundedCard, HttpStatus.FOUND);
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<CardDTO>> getAllCards(){
        List<CardDTO> cardsList = cardService.getAllCards();
        return new ResponseEntity<>(cardsList, HttpStatus.FOUND);
    }

    @PostMapping("/update/{cardId}")
    public ResponseEntity<CardDTO> updateCard(@PathVariable UUID cardId, @RequestBody CardDTO cardDTO){
        CardDTO updatedCard = cardService.updateCard(cardId, cardDTO);
        return new ResponseEntity<>(updatedCard, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/delete/{cardId}")
    public void deleteCard(@PathVariable UUID cardId){
        cardService.deleteCard(cardId);
    }
}
