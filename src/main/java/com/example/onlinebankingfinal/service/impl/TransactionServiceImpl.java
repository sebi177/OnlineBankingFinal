package com.example.onlinebankingfinal.service.impl;

import com.example.onlinebankingfinal.dto.*;
import com.example.onlinebankingfinal.mapper.TransactionMapper;
import com.example.onlinebankingfinal.model.Transaction;
import com.example.onlinebankingfinal.model.enums.TransactionType;
import com.example.onlinebankingfinal.repository.TransactionRepository;
import com.example.onlinebankingfinal.service.AccountService;
import com.example.onlinebankingfinal.service.CardService;
import com.example.onlinebankingfinal.service.TransactionService;
import com.example.onlinebankingfinal.service.exception.TransactionNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository transactionRepository;
    private final TransactionMapper transactionMapper;
    private final AccountService accountService;

    @Override
    public TransactionDTO createTransaction(TransactionFullDTO transaction) {
        Transaction newTransaction = transactionMapper.toTransaction(transaction);
        return transactionMapper.toDto(transactionRepository.save(newTransaction));
    }

    @Override
    public Transaction getById(UUID transactionId) {
        return transactionRepository.findById(transactionId)
                .orElseThrow(() -> new TransactionNotFoundException("Transaction not found!"));
    }

    @Override
    public TransactionDTO getDtoById(UUID transactionId) {
        return transactionMapper.toDto(getById(transactionId));
    }

    @Override
    public List<TransactionDTO> getAllTransaction() {
        return transactionMapper.listToDto(transactionRepository.findAll());
    }

    @Override
    public TransactionDTO deleteTransaction(UUID transactionId) {
        Transaction existingTransaction = getById(transactionId);
        existingTransaction.setTransactionType(TransactionType.DELETED);
        transactionRepository.save(existingTransaction);
        return transactionMapper.toDto(existingTransaction);
    }

    @Override
    public List<TransactionFullDTO> historyTransactions(UUID accountId) {
        return transactionMapper.listToFullDto(transactionRepository.historyTransactions(accountId));
    }

    @Override
    public TransactionFullDTO performTransaction(TransactionFullDTO transaction) {
        Transaction thisTransaction = transactionMapper.toTransaction(transaction);
        transactionRepository.save(thisTransaction);
        accountService.updateBalance(thisTransaction);
        return transaction;
    }

    @Override
    public TransactionStatisticsDTO accountStatistic(UUID accountId) {
        return transactionRepository.calculateStatisticsForAccount(accountId);
    }

}











