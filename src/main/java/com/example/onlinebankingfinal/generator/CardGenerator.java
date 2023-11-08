package com.example.onlinebankingfinal.generator;

import com.example.onlinebankingfinal.model.Client;
import com.example.onlinebankingfinal.model.enums.CardType;
import lombok.Getter;

import java.util.Random;

@Getter
public class CardGenerator {

    public String generateCardHolder(Client client){
        return client.getFirstName() + client.getLastName();
    }

    public String generateCardNumber(CardType cardType) {
        int startingDigit;

        if (cardType == CardType.VISA) {
            startingDigit = 4;
        } else {
            startingDigit = 5;
        }

        StringBuilder cardNumber = new StringBuilder(String.valueOf(startingDigit));

        for (int i = 0; i < 14; i++) {
            int randomDigit = new Random().nextInt(10);
            cardNumber.append(randomDigit);
        }

        return cardNumber.toString();
    }
}
