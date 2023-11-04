package com.example.onlinebankingfinal.model;

import com.example.onlinebankingfinal.model.enums.AccountStatus;
import com.example.onlinebankingfinal.model.enums.AccountType;
import com.example.onlinebankingfinal.model.enums.CurrencyCode;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "accounts")
public class Account {

    @Id
    @Column(name = "account_id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID accountId;

    @Column(name = "account_name")
    private String accountName;

    @Enumerated(EnumType.STRING)
    @Column(name = "account_type")
    private AccountType accountType;

    @Enumerated(EnumType.STRING)
    @Column(name = "account_status")
    private AccountStatus accountStatus;

    @Column(name = "account_balance")
    private BigDecimal accountBalance;

    @Enumerated(EnumType.STRING)
    @Column(name = "account_currency_code")
    private CurrencyCode accountCurrencyCode;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", updatable = false)
    private Timestamp createdAt;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "client_id", referencedColumnName = "client_id")
    private Client client;

//    @OneToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "card_id", referencedColumnName = "card_id")
//    private Card card;

    @OneToMany(mappedBy = "account", fetch = FetchType.EAGER)
    private List<Agreement> agreementList;

    @OneToMany(mappedBy = "debitAccount", fetch = FetchType.EAGER)
    private Set<Transaction> debitTransaction;

    @OneToMany(mappedBy = "creditAccount", fetch = FetchType.EAGER)
    private Set<Transaction> creditTransaction;

}


//          Account
//        - id: UUID
//        - name: String
//        - type: AccountType
//        - status: AccountStatus
//        - currencyCode : CurrencyCode
//        - createdAt: Timestamp
//        - updatedAt: Timestamp
//        - client: Client
//        - agreements: List<Agreement>
//        - debitTransactions: Set<Transaction»
//        - creditTransactions: Set<Transaction>
//        + equals(o: Object) : boolean
//        + hashCode(): int
//        + to String() : String
