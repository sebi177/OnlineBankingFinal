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
    public ResponseEntity<TransactionDTO> createTransaction(@RequestBody Transaction transaction) {
        TransactionDTO createdTransaction = transactionService.createTransaction(transaction);
        return new ResponseEntity<>(createdTransaction, HttpStatus.CREATED);
    }

    @GetMapping("/find/{transactionId}")
    public ResponseEntity<TransactionDTO> getTransactionById(@PathVariable UUID transactionId) {
        TransactionDTO foundedTransaction = transactionService.getDtoById(transactionId);
        return new ResponseEntity<>(foundedTransaction, HttpStatus.FOUND);
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<TransactionDTO>> getAllTransactions(){
        List<TransactionDTO> transactionList = transactionService.getAllTransaction();
        return new ResponseEntity<>(transactionList, HttpStatus.FOUND);
    }

    @PostMapping("/update/{transactionId}")
    public ResponseEntity<TransactionDTO> updateTransaction(@PathVariable UUID transactionId, @RequestBody TransactionDTO transactionDTO){
        TransactionDTO updatedTransaction = transactionService.updateTransaction(transactionId, transactionDTO);
        return new ResponseEntity<>(updatedTransaction, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/delete/{transactionId}")
    public void deleteTransaction(@PathVariable UUID transactionId){
        transactionService.deleteTransaction(transactionId);
    }
}
