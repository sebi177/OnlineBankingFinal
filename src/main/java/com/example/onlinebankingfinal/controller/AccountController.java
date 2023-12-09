package com.example.onlinebankingfinal.controller;

import com.example.onlinebankingfinal.dto.*;
import com.example.onlinebankingfinal.model.Account;
import com.example.onlinebankingfinal.model.Card;
import com.example.onlinebankingfinal.service.AccountService;
import com.example.onlinebankingfinal.service.AgreementService;
import com.example.onlinebankingfinal.service.CardService;
import com.example.onlinebankingfinal.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
    @ResponseStatus(HttpStatus.OK)
    public AccountDTO createAccount(@RequestBody Account account) {
        return accountService.createAccount(account);
    }

    @GetMapping("/find/{accountId}")
    @ResponseStatus(HttpStatus.OK)
    public AccountDTO getAccountById(@PathVariable UUID accountId) {
        return accountService.getDtoById(accountId);
    }

    @GetMapping("/findAll")
    @ResponseStatus(HttpStatus.OK)
    public List<AccountDTO> getAllAccounts() {
        return accountService.getAllAccounts();
    }

    @PostMapping("/update/{accountId}")
    @ResponseStatus(HttpStatus.OK)
    public AccountDTO updateAccount(@PathVariable UUID accountId, @RequestBody AccountDTO accountDTO) {
        return accountService.updateAccount(accountId, accountDTO);
    }

    @PostMapping("/delete/{accountId}")
    @ResponseStatus(HttpStatus.OK)
    public AccountDTO deleteAccount(@PathVariable UUID accountId) {
        return accountService.deleteAccount(accountId);
    }

    @GetMapping("transactions/{accountId}")
    @ResponseStatus(HttpStatus.OK)
    public List<TransactionFullDTO> getTransactions(@PathVariable UUID accountId) {
        return transactionService.historyTransactions(accountId);
    }

    @PostMapping("/{accountId}/createCard")
    @ResponseStatus(HttpStatus.OK)
    public CardFullDTO createCardByAccount(@PathVariable UUID accountId, @RequestBody Card card) {
        return cardService.createCardByAccount(accountId, card);
    }

    @PostMapping("/{accountId}/createAgreement")
    @ResponseStatus(HttpStatus.OK)
    public AgreementFullDTO createAgreementByAccount(@PathVariable UUID accountId, @RequestBody AgreementFullDTO agreement) {
        return agreementService.createAgreementByAccount(accountId, agreement);
    }

    @GetMapping("/{cardNumber}")
    @ResponseStatus(HttpStatus.OK)
    public AccountFullDTO getAccountByCardNumber(@PathVariable String cardNumber) {
        return accountService.getAccountByCardNumber(cardNumber);
    }

    @GetMapping("/statistic/{accountId}")
    @ResponseStatus(HttpStatus.OK)
    public TransactionStatisticsDTO statistic(@PathVariable UUID accountId) {
        return transactionService.accountStatistic(accountId);
    }
}
