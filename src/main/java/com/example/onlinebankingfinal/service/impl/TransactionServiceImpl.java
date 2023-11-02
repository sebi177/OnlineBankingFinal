package com.example.onlinebankingfinal.service.impl;

import com.example.onlinebankingfinal.dto.TransactionDTO;
import com.example.onlinebankingfinal.mapper.TransactionMapper;
import com.example.onlinebankingfinal.model.Transaction;
import com.example.onlinebankingfinal.repository.TransactionRepository;
import com.example.onlinebankingfinal.service.TransactionService;
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

    @Override
    public TransactionDTO createTransaction(Transaction transaction){
        return transactionMapper.toDto(transactionRepository.save(transaction));
    }

    @Override
    public Transaction getById(UUID transactionId){
        return transactionRepository.findById(transactionId)
                .orElseThrow(() -> new EntityNotFoundException("Transaction not found!"));
    }

    @Override
    public TransactionDTO getDtoById(UUID transactionId){
        return transactionMapper.toDto(getById(transactionId));
    }

    @Override
    public List<TransactionDTO> getAllTransaction(){
        return transactionMapper.listToDto(transactionRepository.findAll());
    }

    @Override
    public TransactionDTO updateTransaction(UUID transactionId, TransactionDTO transactionDTO){
        Transaction existingTransaction = getById(transactionId);
        transactionMapper.updateTransactionFromDTO(transactionDTO, existingTransaction);
        return transactionMapper.toDto(transactionRepository.save(existingTransaction));
    }

    @Override
    public void deleteTransaction(UUID transactionId){
        Transaction existingTransaction = transactionRepository.findById(transactionId)
                .orElseThrow(() -> new EntityNotFoundException("Transaction not found!"));
        transactionRepository.delete(existingTransaction);
    }
}
