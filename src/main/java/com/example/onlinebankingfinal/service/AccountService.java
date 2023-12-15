package com.example.onlinebankingfinal.service;

import com.example.onlinebankingfinal.dto.AccountDTO;
import com.example.onlinebankingfinal.dto.AccountFullDTO;
import com.example.onlinebankingfinal.model.Account;
import com.example.onlinebankingfinal.model.Transaction;

import java.util.List;
import java.util.UUID;

public interface AccountService {

    AccountDTO createAccount(AccountFullDTO account);

    AccountDTO getDtoById(UUID accountId);

    Account getById(UUID accountId);

    List<AccountDTO> getAllAccounts();

    AccountDTO updateAccount(UUID accountId, AccountDTO accountDTO);

    AccountDTO deleteAccount(UUID accountId);

    void updateBalance(Transaction transaction);

    AccountFullDTO createAccountByClient(UUID clientId, Account account);

    AccountFullDTO getAccountByCardNumber(String cardNumber);
}
