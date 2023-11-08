package com.example.onlinebankingfinal.model;

import com.example.onlinebankingfinal.model.enums.CurrencyCode;
import com.example.onlinebankingfinal.model.enums.TransactionType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "transactions")
public class Transaction {

    @Id
    @Column(name = "transaction_id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID transactionId;

    @Enumerated(EnumType.STRING)
    @Column(name = "transaction_type")
    private TransactionType transactionType;

    @Column(name = "transaction_amount")
    private Double transactionAmount;

    @Enumerated(EnumType.STRING)
    @Column(name = "currency_code")
    private CurrencyCode transactionCurrencyCode;

    @Column(name = "transaction_description")
    private String transactionDescription;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "debit_account_id", referencedColumnName = "account_id")
    private Account debitAccount;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "credit_account_id", referencedColumnName = "account_id")
    private Account creditAccount;

    @Override
    public String toString() {
        return "Transaction{" +
                "transactionId=" + transactionId +
                ", transactionType=" + transactionType +
                ", transactionAmount=" + transactionAmount +
                ", transactionCurrencyCode=" + transactionCurrencyCode +
                ", transactionDescription='" + transactionDescription + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }

}

//            Transaction
//        - id: UUID
//        - type: TransactionType
//        - amount: double
//        - description: String
//        - createdAt: Timestamp
//        - debitAccount: Account
//        - creditAccount: Account
//        + equals(o: Object): boolean
//        + hashCode() : int
//        + toString() : String
