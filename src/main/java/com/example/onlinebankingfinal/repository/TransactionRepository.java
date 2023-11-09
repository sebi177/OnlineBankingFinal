package com.example.onlinebankingfinal.repository;

import com.example.onlinebankingfinal.dto.TransactionStatisticsDTO;
import com.example.onlinebankingfinal.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, UUID> {

    @Query("select t from Transaction t where t.creditAccount.accountId = :id or t.debitAccount.accountId = :id")
    List<Transaction> historyTransactions(@Param("id")UUID id);

    @Query("SELECT new com.example.onlinebankingfinal.dto.TransactionStatisticsDTO(" +
            "SUM(CASE WHEN t.creditAccount.accountId = :accountId THEN t.transactionAmount ELSE 0 END), " +
            "SUM(CASE WHEN t.debitAccount.accountId = :accountId THEN t.transactionAmount ELSE 0 END), " +
            "(SUM(CASE WHEN t.creditAccount.accountId = :accountId THEN t.transactionAmount ELSE 0 END) / SUM(t.transactionAmount)) * 100, " +
            "(SUM(CASE WHEN t.debitAccount.accountId = :accountId THEN t.transactionAmount ELSE 0 END) / SUM(t.transactionAmount)) * 100) " +
            "FROM Transaction t " +
            "WHERE t.debitAccount.accountId = :accountId OR t.creditAccount.accountId = :accountId")
    TransactionStatisticsDTO calculateStatisticsForAccount(@Param("accountId") UUID accountId);

}
