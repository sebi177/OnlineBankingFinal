package com.example.onlinebankingfinal.repository;

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
}
