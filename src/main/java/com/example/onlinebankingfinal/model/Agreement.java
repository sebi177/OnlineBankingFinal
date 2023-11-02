package com.example.onlinebankingfinal.model;

import com.example.onlinebankingfinal.model.enums.AgreementStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "agreements")
public class Agreement {

    @Id
    @Column(name = "agreement_id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID agreementId;

    @Column(name = "interest_rate")
    private Double interestRate;

    @Enumerated(EnumType.STRING)
    @Column(name = "agreement_status")
    private AgreementStatus agreementStatus;

    @Column(name = "agreement_sum")
    private BigDecimal agreementSum;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime updatedAt;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id", referencedColumnName = "product_id")
    private Product product;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "account_id", referencedColumnName = "account_id")
    private Account account;

}

//          Agreement
//        - id: UUID
//        - interestRate: double
//        - status: AgreementStatus
//        - sum: double
//        - createdAt: Timestamp
//        - updatedAt: Timestamp
//        - product: Product
//        - account: Account
//        + equals(o: Object): boolean
//        + hashCode(): int
//        + toString() : String
