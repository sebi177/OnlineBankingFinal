package com.example.onlinebankingfinal.controller;

import com.example.onlinebankingfinal.dto.TransactionCardToCard;
import com.example.onlinebankingfinal.dto.TransactionDTO;
import com.example.onlinebankingfinal.dto.TransactionFullDTO;
import com.example.onlinebankingfinal.model.Transaction;
import com.example.onlinebankingfinal.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/transaction")
public class TransactionController {
    private final TransactionService transactionService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.OK)
    public TransactionDTO createTransaction(@RequestBody TransactionFullDTO transaction) {
        return transactionService.createTransaction(transaction);
    }

    @GetMapping("/find/{transactionId}")
    @ResponseStatus(HttpStatus.OK)
    public TransactionDTO getTransactionById(@PathVariable UUID transactionId) {
        return transactionService.getDtoById(transactionId);
    }

    @GetMapping("/findAll")
    @ResponseStatus(HttpStatus.OK)
    public List<TransactionDTO> getAllTransactions(){
        return transactionService.getAllTransaction();
    }

    @PostMapping("/delete/{transactionId}")
    @ResponseStatus(HttpStatus.OK)
    public TransactionDTO deleteTransaction(@PathVariable UUID transactionId){
        return transactionService.deleteTransaction(transactionId);
    }

    @PostMapping("/transfer")
    @ResponseStatus(HttpStatus.OK)
    public TransactionFullDTO performTransaction(@RequestBody TransactionFullDTO transaction){
        return transactionService.performTransaction(transaction);
    }

}
