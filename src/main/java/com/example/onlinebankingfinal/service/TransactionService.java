package com.example.onlinebankingfinal.service;

import com.example.onlinebankingfinal.dto.TransactionDTO;
import com.example.onlinebankingfinal.model.Transaction;

import java.util.List;
import java.util.UUID;

public interface TransactionService {
    TransactionDTO createTransaction(Transaction transaction);

    Transaction getById(UUID transactionId);

    TransactionDTO getDtoById(UUID transactionId);

    List<TransactionDTO> getAllTransaction();

    TransactionDTO updateTransaction(UUID transactionId, TransactionDTO transactionDTO);

    void deleteTransaction(UUID transactionId);
}
