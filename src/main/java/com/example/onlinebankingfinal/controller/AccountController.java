package com.example.onlinebankingfinal.controller;

import com.example.onlinebankingfinal.dto.*;
import com.example.onlinebankingfinal.model.Account;
import com.example.onlinebankingfinal.model.Agreement;
import com.example.onlinebankingfinal.model.Card;
import com.example.onlinebankingfinal.service.AccountService;
import com.example.onlinebankingfinal.service.AgreementService;
import com.example.onlinebankingfinal.service.CardService;
import com.example.onlinebankingfinal.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/account")
public class AccountController {
    private final AccountService accountService;
    private final TransactionService transactionService;
    private final CardService cardService;
    private final AgreementService agreementService;

    @PostMapping("/create")
    public ResponseEntity<AccountDTO> createAccount(@RequestBody Account account) {
        AccountDTO createdAccount = accountService.createAccount(account);
        return new ResponseEntity<>(createdAccount, HttpStatus.CREATED);
    }

    @GetMapping("/find/{accountId}")
    public ResponseEntity<AccountDTO> getAccountById(@PathVariable UUID accountId) {
        AccountDTO foundedAccount = accountService.getDtoById(accountId);
        return new ResponseEntity<>(foundedAccount, HttpStatus.FOUND);
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<AccountDTO>> getAllAccounts(){
        List<AccountDTO> accountList = accountService.getAllAccounts();
        return new ResponseEntity<>(accountList, HttpStatus.FOUND);
    }

    @PostMapping("/update/{accountId}")
    public ResponseEntity<AccountDTO> updateAccount(@PathVariable UUID accountId,@RequestBody AccountDTO accountDTO){
        AccountDTO updatedAccount = accountService.updateAccount(accountId, accountDTO);
        return new ResponseEntity<>(updatedAccount, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/delete/{accountId}")
    public void deleteAccount(@PathVariable UUID accountId){
        accountService.deleteAccount(accountId);
    }

    @GetMapping("transactions/{accountId}")
    public ResponseEntity<List<TransactionFullDTO>> getTransactions(@PathVariable UUID accountId){
        List<TransactionFullDTO> transactionFullDTOList = transactionService.historyTransactions(accountId);
        return new ResponseEntity<>(transactionFullDTOList, HttpStatus.ACCEPTED);
    }

    @PostMapping("/{accountId}/createCard")
    public ResponseEntity<CardFullDTO> createCardByAccount(@PathVariable UUID accountId,@RequestBody Card card){
        CardFullDTO thisCard = cardService.createCardByAccount(accountId, card);
        return new ResponseEntity<>(thisCard, HttpStatus.CREATED);
    }

    @PostMapping("/{accountId}/createAgreement")
    public ResponseEntity<AgreementFullDTO> createAgreementByAccount(@PathVariable UUID accountId,@RequestBody AgreementFullDTO agreement){
        AgreementFullDTO thisAgreement = agreementService.createAgreementByAccount(accountId, agreement);
        return new ResponseEntity<>(thisAgreement, HttpStatus.CREATED);
    }

    @GetMapping("/{cardNumber}")
    public ResponseEntity<AccountFullDTO> getAccountByCardNumber(@PathVariable String cardNumber){
        AccountFullDTO thisAccount = accountService.getAccountByCardNumber(cardNumber);
        return new ResponseEntity<>(thisAccount, HttpStatus.FOUND);
    }

    @PostMapping("/{accountId}/generateCard")
    public ResponseEntity<CardFullDTO> generateCard(@PathVariable UUID accountId){
        Account thisAccount = accountService.getById(accountId);
        CardFullDTO thisCard = cardService.generateCard(thisAccount);
        return new ResponseEntity<>(thisCard, HttpStatus.CREATED);
    }

}
