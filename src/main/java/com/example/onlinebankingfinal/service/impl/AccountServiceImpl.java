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
    public AccountDTO createAccount(Account account) {
        return accountMapper.toDTO(accountRepository.save(account));
    }

    @Override
    public AccountDTO getDtoById(UUID accountId) {
        return accountMapper.toDTO(getById(accountId));
    }

    @Override
    public Account getById(UUID accountId) {
        return accountRepository.findById(accountId)
                .orElseThrow(() -> new EntityNotFoundException("Account not found!"));
    }

    @Override
    public List<AccountDTO> getAllAccounts() {
        return accountMapper.toDTO(accountRepository.findAll());
    }

    @Override
    public AccountDTO updateAccount(UUID accountId, AccountDTO accountDTO) {
        Account existingAccount = getById(accountId);
        accountMapper.updateAccountFromDTO(accountDTO, existingAccount);
        return accountMapper.toDTO(accountRepository.save(existingAccount));
    }

    @Override
    public void deleteAccount(UUID accountId) {
        Account existingAccount = accountRepository.findById(accountId)
                .orElseThrow(() -> new EntityNotFoundException("Account not found!"));
        accountRepository.delete(existingAccount);
    }
}
