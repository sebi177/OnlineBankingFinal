package com.example.onlinebankingfinal.service;

import com.example.onlinebankingfinal.dto.TransactionDTO;
import com.example.onlinebankingfinal.model.Transaction;

import java.util.List;
import java.util.UUID;

public interface TransactionService {
    Transaction createTransaction(Transaction transaction);

    Transaction getById(UUID transactionId);

    List<Transaction> getAllTransaction();

    Transaction updateTransaction(UUID transactionId, TransactionDTO transactionDTO);

    void deleteTransaction(UUID transactionId);
}
