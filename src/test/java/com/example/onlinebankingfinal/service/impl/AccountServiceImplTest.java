package com.example.onlinebankingfinal.service.impl;


import com.example.onlinebankingfinal.dto.AccountDTO;
import com.example.onlinebankingfinal.mapper.AccountMapper;
import com.example.onlinebankingfinal.model.Account;
import com.example.onlinebankingfinal.repository.AccountRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
public class AccountServiceImplTest {

    @InjectMocks
    private AccountServiceImpl accountService;

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private AccountMapper accountMapper;

    @BeforeEach
    public void setUp() {
        // Inițializați orice configurații sau așteptări aici, dacă este necesar.
    }

    @Test
    public void testCreateAccount() {
        Account newAccount = new Account();
        when(accountRepository.save(newAccount)).thenReturn(newAccount);

        Account createdAccount = accountService.createAccount(newAccount);

        assertNotNull(createdAccount);
        // Asigurați-vă că obiectul de returnare este același cu cel creat.
        assertSame(newAccount, createdAccount);
    }

    @Test
    public void testGetAccountById() {
        UUID accountId = UUID.randomUUID();
        Account existingAccount = new Account();
        when(accountRepository.findById(accountId)).thenReturn(Optional.of(existingAccount));

        Account retrievedAccount = accountService.getById(accountId);

        assertNotNull(retrievedAccount);
        // Asigurați-vă că obiectul returnat este același cu cel existent în repo.
        assertSame(existingAccount, retrievedAccount);
    }

    @Test
    public void testGetAccountByIdNotFound() {
        UUID accountId = UUID.randomUUID();
        when(accountRepository.findById(accountId)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> accountService.getById(accountId));
    }

    @Test
    public void testGetAllAccounts() {
        Account account1 = new Account();
        Account account2 = new Account();
        when(accountRepository.findAll()).thenReturn(List.of(account1, account2));

        List<Account> allAccounts = accountService.getAllAccounts();

        assertEquals(2, allAccounts.size());
    }

    @Test
    public void testUpdateAccount() {
        UUID accountId = UUID.randomUUID();
        AccountDTO accountDTO = new AccountDTO();
        Account existingAccount = new Account();
        when(accountRepository.findById(accountId)).thenReturn(Optional.of(existingAccount));

        // Simulăm comportamentul pentru metoda "updateAccountFromDTO" din AccountMapper, dacă este necesar.

        Account updatedAccount = accountService.updateAccount(accountId, accountDTO);

//        assertNotNull(updatedAccount);
        // Asigurați-vă că obiectul de returnare este același cu cel existent.
//        assertSame(existingAccount, updatedAccount);
        // Asigurați-vă că metoda "updateAccountFromDTO" a fost apelată (cu Mockito).
        Mockito.verify(accountMapper).updateAccountFromDTO(accountDTO, existingAccount);
    }

    @Test
    public void testDeleteAccount() {
        UUID accountId = UUID.randomUUID();
        Account existingAccount = new Account();
        when(accountRepository.findById(accountId)).thenReturn(Optional.of(existingAccount));

        accountService.deleteAccount(accountId);

        // Asigurați-vă că metoda "delete" a fost apelată (cu Mockito).
        Mockito.verify(accountRepository).delete(existingAccount);
    }

    @Test
    public void testDeleteAccountNotFound() {
        UUID accountId = UUID.randomUUID();
        when(accountRepository.findById(accountId)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> accountService.deleteAccount(accountId));
    }
}

