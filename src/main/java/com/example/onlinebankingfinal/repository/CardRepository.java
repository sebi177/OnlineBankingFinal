package com.example.onlinebankingfinal.repository;

import com.example.onlinebankingfinal.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CardRepository extends JpaRepository<Card, UUID> {
    Card findByCardNumber(String cardNumber);
}
