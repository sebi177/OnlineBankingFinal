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
}
