package com.example.onlinebankingfinal.service.impl;

import com.example.onlinebankingfinal.dto.AccountDTO;
import com.example.onlinebankingfinal.dto.AccountFullDTO;
import com.example.onlinebankingfinal.mapper.AccountMapper;
import com.example.onlinebankingfinal.model.Account;
import com.example.onlinebankingfinal.model.Card;
import com.example.onlinebankingfinal.model.Client;
import com.example.onlinebankingfinal.model.Transaction;
import com.example.onlinebankingfinal.model.enums.AccountStatus;
import com.example.onlinebankingfinal.model.enums.CurrencyCode;
import com.example.onlinebankingfinal.repository.AccountRepository;
import com.example.onlinebankingfinal.repository.CardRepository;
import com.example.onlinebankingfinal.service.AccountService;
import com.example.onlinebankingfinal.service.ClientService;
import com.example.onlinebankingfinal.service.exception.AccountNotFoundException;
import com.example.onlinebankingfinal.service.exception.NegativeBalanceThrowException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;
    private final ClientService clientService;
    private final CardRepository cardRepository;

    @Override
    public AccountDTO createAccount(AccountFullDTO account) {
        Account newAccount = accountMapper.toEntity(account);
        return accountMapper.toDTO(accountRepository.save(newAccount));
    }

    @Override
    public AccountDTO getDtoById(UUID accountId) {
        return accountMapper.toDTO(getById(accountId));
    }

    @Override
    public Account getById(UUID accountId) {
        return accountRepository.findById(accountId)
                .orElseThrow(() -> new AccountNotFoundException("Account not found!"));
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
    public AccountDTO deleteAccount(UUID accountId) {
        Account existingAccount = getById(accountId);
        existingAccount.setAccountStatus(AccountStatus.DELETED);
        accountRepository.save(existingAccount);
        return accountMapper.toDTO(existingAccount);
    }

    @Override
    public void updateBalance(Transaction transaction) {
        Account sourceAccount = getById(transaction.getDebitAccount().getAccountId());
        Account destinationAccount = getById(transaction.getCreditAccount().getAccountId());

        CurrencyCode sourceCurrencyCode = sourceAccount.getAccountCurrencyCode();
        CurrencyCode destinationCurrencyCode = destinationAccount.getAccountCurrencyCode();
        CurrencyCode transactionCurrencyCode = transaction.getTransactionCurrencyCode();

        double sourceBalance = sourceAccount.getAccountBalance() * sourceCurrencyCode.getExchangeRateToEUR();
        double destinationBalance = destinationAccount.getAccountBalance() * destinationCurrencyCode.getExchangeRateToEUR();
        double transactionAmount = transaction.getTransactionAmount() * transactionCurrencyCode.getExchangeRateToEUR();

        double newSourceBalance = (sourceBalance - transactionAmount) / sourceCurrencyCode.getExchangeRateToEUR();
        double newDestinationBalance = (destinationBalance + transactionAmount) / destinationCurrencyCode.getExchangeRateToEUR();

        if (newSourceBalance < 0) {
            throw new NegativeBalanceThrowException("Not enough money!");
        } else {
            sourceAccount.setAccountBalance(newSourceBalance);
            destinationAccount.setAccountBalance(newDestinationBalance);

            accountRepository.save(sourceAccount);
            accountRepository.save(destinationAccount);
        }
    }

    @Override
    public AccountFullDTO createAccountByClient(UUID clientId, Account account) {
        Client client = clientService.getById(clientId);
        account.setClient(client);
        accountRepository.save(account);
        return accountMapper.toFullDTO(account);
    }

    @Override
    public AccountFullDTO getAccountByCardNumber(String cardNumber) {
        Card thisCard = cardRepository.findByCardNumber(cardNumber);
        Account thisAccount = thisCard.getAccount();
        return accountMapper.toFullDTO(thisAccount);
    }

}
