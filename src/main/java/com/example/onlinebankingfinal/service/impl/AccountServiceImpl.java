package com.example.onlinebankingfinal.service.impl;

import com.example.onlinebankingfinal.dto.AccountDTO;
import com.example.onlinebankingfinal.mapper.AccountMapper;
import com.example.onlinebankingfinal.model.Account;
import com.example.onlinebankingfinal.repository.AccountRepository;
import com.example.onlinebankingfinal.service.AccountService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;

    @Override
    public Account createAccount(Account account) {
        return accountRepository.save(account);
    }

    @Override
    public Account getById(UUID accountId) {
        return accountRepository.findById(accountId)
                .orElseThrow(() -> new EntityNotFoundException("Account not found!"));
    }

    @Override
    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    @Override
    public Account updateAccount(UUID accountId, AccountDTO accountDTO) {
        Account existingAccount = getById(accountId);
        accountMapper.updateAccountFromDTO(accountDTO, existingAccount);
        return accountRepository.save(existingAccount);
    }

    @Override
    public void deleteAccount(UUID accountId) {
        Account existingAccount = accountRepository.findById(accountId)
                .orElseThrow(() -> new EntityNotFoundException("Account not found!"));
        accountRepository.delete(existingAccount);
    }
}
