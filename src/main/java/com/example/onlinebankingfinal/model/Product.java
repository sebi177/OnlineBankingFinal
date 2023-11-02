package com.example.onlinebankingfinal.model;

import com.example.onlinebankingfinal.model.enums.CurrencyCode;
import com.example.onlinebankingfinal.model.enums.ProductStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "products")
public class Product {

    @Id
    @Column(name = "product_id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID productId;

    @Column(name = "product_name")
    private String productName;

    @Enumerated(EnumType.STRING)
    @Column(name = "product_status")
    private ProductStatus productStatus;

    @Enumerated(EnumType.STRING)
    @Column(name = "product_currency_code")
    private CurrencyCode productCurrencyCode;

    @Column(name = "interest_rate")
    private Double interestRate;

    @Column(name = "product_limit")
    private Integer productLimit;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "manager_id", referencedColumnName = "manager_id")
    private Manager manager;

    @OneToMany(mappedBy = "product", fetch = FetchType.EAGER)
    private List<Agreement> agreementList;
}

//            Product
//        - id: UUID
//        - name: String
//        - status: ProductStatus
//        - currencyCode: CurrencyCode
//        - interestRate: double
//        - productLimit: int
//        - createdAt: Timestamp
//        - updatedAt: Timestamp
//        - manager: Manager
//        - agreements : List<Agreement>
//        + equals(o: Object) : boolean
//        + hashCode(): int
//        + toString() : String
