package com.example.onlinebankingfinal.controller;

import com.example.onlinebankingfinal.dto.TransactionDTO;
import com.example.onlinebankingfinal.model.Transaction;
import com.example.onlinebankingfinal.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/transaction")
public class TransactionController {
    private final TransactionService transactionService;

    @PostMapping("/create")
    public ResponseEntity<Transaction> createTransaction(@RequestBody Transaction transaction) {
        Transaction createdTransaction = transactionService.createTransaction(transaction);
        return new ResponseEntity<>(createdTransaction, HttpStatus.CREATED);
    }

    @GetMapping("/find/{transactionId}")
    public ResponseEntity<Transaction> getTransactionById(@PathVariable UUID transactionId) {
        Transaction foundedTransaction = transactionService.getById(transactionId);
        return new ResponseEntity<>(foundedTransaction, HttpStatus.FOUND);
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<Transaction>> getAllTransactions(){
        List<Transaction> transactionList = transactionService.getAllTransaction();
        return new ResponseEntity<>(transactionList, HttpStatus.FOUND);
    }

    @PostMapping("/update/{transactionId}")
    public ResponseEntity<Transaction> updateTransaction(@PathVariable UUID transactionId, @RequestBody TransactionDTO transactionDTO){
        Transaction updatedTransaction = transactionService.updateTransaction(transactionId, transactionDTO);
        return new ResponseEntity<>(updatedTransaction, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/delete/{transactionId}")
    public void deleteTransaction(@PathVariable UUID transactionId){
        transactionService.deleteTransaction(transactionId);
    }
}
