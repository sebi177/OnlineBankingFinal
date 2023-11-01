package com.example.onlinebankingfinal.service;

import com.example.onlinebankingfinal.dto.AccountDTO;
import com.example.onlinebankingfinal.model.Account;

import java.util.List;
import java.util.UUID;

public interface AccountService {

    Account createAccount(Account account);

    Account getById(UUID accountId);

    List<Account> getAllAccounts();

    Account updateAccount(UUID accountId, AccountDTO accountDTO);

    void deleteAccount(UUID accountId);
}
