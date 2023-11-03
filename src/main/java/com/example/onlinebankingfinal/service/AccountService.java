package com.example.onlinebankingfinal.service;

import com.example.onlinebankingfinal.dto.AccountDTO;
import com.example.onlinebankingfinal.model.Account;
import com.example.onlinebankingfinal.model.Client;

import java.util.List;
import java.util.UUID;

public interface AccountService {

    AccountDTO createAccount(Account account);

    AccountDTO getDtoById(UUID accountId);

    Account getById(UUID accountId);

    List<AccountDTO> getAllAccounts();

    AccountDTO updateAccount(UUID accountId, AccountDTO accountDTO);

    void deleteAccount(UUID accountId);

}
