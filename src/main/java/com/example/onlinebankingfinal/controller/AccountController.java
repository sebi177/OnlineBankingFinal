package com.example.onlinebankingfinal.controller;

import com.example.onlinebankingfinal.dto.AccountDTO;
import com.example.onlinebankingfinal.model.Account;
import com.example.onlinebankingfinal.service.AccountService;
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

    @PostMapping("/create")
    public ResponseEntity<Account> createAccount(@RequestBody Account account) {
        Account createdAccount = accountService.createAccount(account);
        return new ResponseEntity<>(createdAccount, HttpStatus.CREATED);
    }

    @GetMapping("/find/{accountId}")
    public ResponseEntity<Account> getAccountById(@PathVariable UUID accountId) {
        Account foundedAccount = accountService.getById(accountId);
        return new ResponseEntity<>(foundedAccount, HttpStatus.FOUND);
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<Account>> getAllAccounts(){
        List<Account> accountList = accountService.getAllAccounts();
        return new ResponseEntity<>(accountList, HttpStatus.FOUND);
    }

    @PostMapping("/update/{accountId}")
    public ResponseEntity<Account> updateAccount(@PathVariable UUID accountId,@RequestBody AccountDTO accountDTO){
        Account updatedAccount = accountService.updateAccount(accountId, accountDTO);
        return new ResponseEntity<>(updatedAccount, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/delete/{accountId}")
    public void deleteAccount(@PathVariable UUID accountId){
        accountService.deleteAccount(accountId);
    }
}
