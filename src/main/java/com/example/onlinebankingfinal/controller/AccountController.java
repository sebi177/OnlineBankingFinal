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

/**
 * The AccountController class is responsible for handling HTTP requests related to account management.
 * It exposes several endpoints for creating, retrieving, updating, and deleting accounts, as well as managing transactions, cards, and agreements.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/account")
public class AccountController {
    /**
     *
     */
    private final AccountService accountService;
    /**
     * This variable represents a TransactionService object.
     */
    private final TransactionService transactionService;
    /**
     *
     */
    private final CardService cardService;
    /**
     *
     */
    private final AgreementService agreementService;

    /**
     * Creates a new account.
     *
     * @param account the account object containing the account details
     * @return the AccountDTO object representing the created account
     */
    @PostMapping("/create")
    @ResponseStatus(HttpStatus.OK)
    public AccountDTO createAccount(@RequestBody AccountFullDTO account) {
        return accountService.createAccount(account);
    }

    /**
     * Retrieves an AccountDTO object by its account ID.
     *
     * @param accountId the unique identifier of the account
     * @return the AccountDTO object associated with the account ID
     */
    @GetMapping("/find/{accountId}")
    @ResponseStatus(HttpStatus.OK)
    public AccountDTO getAccountById(@PathVariable UUID accountId) {
        return accountService.getDtoById(accountId);
    }

    /**
     * Retrieves all accounts.
     *
     * @return A List of AccountDTO objects representing all the accounts.
     */
    @GetMapping("/findAll")
    @ResponseStatus(HttpStatus.OK)
    public List<AccountDTO> getAllAccounts() {
        return accountService.getAllAccounts();
    }

    /**
     * Updates the account with the specified accountId.
     *
     * @param accountId   The unique identifier of the account.
     * @param accountDTO  The updated account data.
     * @return The updated account as an AccountDTO object.
     */
    @PostMapping("/update/{accountId}")
    @ResponseStatus(HttpStatus.OK)
    public AccountDTO updateAccount(@PathVariable UUID accountId, @RequestBody AccountDTO accountDTO) {
        return accountService.updateAccount(accountId, accountDTO);
    }

    /**
     * Deletes an account by its account ID.
     *
     * @param accountId the ID of the account to be deleted
     * @return the deleted AccountDTO
     */
    @PostMapping("/delete/{accountId}")
    @ResponseStatus(HttpStatus.OK)
    public AccountDTO deleteAccount(@PathVariable UUID accountId) {
        return accountService.deleteAccount(accountId);
    }

    /**
     * Retrieves the list of transactions for the specified account.
     *
     * @param accountId the unique identifier of the account
     * @return the list of TransactionFullDTO objects representing the transactions for the account
     */
    @GetMapping("transactions/{accountId}")
    @ResponseStatus(HttpStatus.OK)
    public List<TransactionFullDTO> getTransactions(@PathVariable UUID accountId) {
        return transactionService.historyTransactions(accountId);
    }

    /**
     * Creates a new card for the given account.
     *
     * @param accountId the ID of the account
     * @param card      the card to be created
     * @return the newly created CardFullDTO representing the created card
     */
    @PostMapping("/{accountId}/createCard")
    @ResponseStatus(HttpStatus.OK)
    public CardFullDTO createCardByAccount(@PathVariable UUID accountId, @RequestBody Card card) {
        return cardService.createCardByAccount(accountId, card);
    }

    /**
     * Creates an agreement for an account.
     *
     * @param accountId  The ID of the account to create the agreement for.
     * @param agreement  The agreement data.
     * @return The created agreement information.
     */
    @PostMapping("/{accountId}/createAgreement")
    @ResponseStatus(HttpStatus.OK)
    public AgreementFullDTO createAgreementByAccount(@PathVariable UUID accountId, @RequestBody AgreementFullDTO agreement) {
        return agreementService.createAgreementByAccount(accountId, agreement);
    }

    /**
     * Retrieves the information of an account based on the provided card number.
     *
     * @param cardNumber The card number associated with the account.
     * @return The AccountFullDTO object containing the account information.
     */
    @GetMapping("/{cardNumber}")
    @ResponseStatus(HttpStatus.OK)
    public AccountFullDTO getAccountByCardNumber(@PathVariable String cardNumber) {
        return accountService.getAccountByCardNumber(cardNumber);
    }

    /**
     * Retrieves the transaction statistics for a given account.
     *
     * @param accountId the ID of the account to retrieve statistics for
     * @return the transaction statistics for the account
     */
    @GetMapping("/statistic/{accountId}")
    @ResponseStatus(HttpStatus.OK)
    public TransactionStatisticsDTO statistic(@PathVariable UUID accountId) {
        return transactionService.accountStatistic(accountId);
    }
}
