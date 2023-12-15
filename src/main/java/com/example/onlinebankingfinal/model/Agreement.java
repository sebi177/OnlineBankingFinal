package com.example.onlinebankingfinal.model;

import com.example.onlinebankingfinal.model.enums.AgreementStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

/**
 * The Agreement class represents an agreement between a customer and a bank.
 *
 * Each agreement has the following properties:
 * - agreementId: The unique identifier for the agreement (UUID)
 * - interestRate: The interest rate for the agreement (Double)
 * - agreementStatus: The status of the agreement (AgreementStatus enum)
 * - agreementSum: The sum of the agreement (Double)
 * - createdAt: The timestamp when the agreement was created (LocalDateTime)
 * - updatedAt: The timestamp when the agreement was last updated (LocalDateTime)
 * - product: The product associated with the agreement (Product)
 * - account: The account associated with the agreement (Account)
 *
 * The AgreementStatus enum represents the various statuses that an agreement can have:
 * - ACTIVE
 * - PENDING
 * - EXPIRED
 * - SUSPENDED
 * - CANCELED
 * - TERMINATED
 * - UNDER_REVIEW
 * - DELETED
 *
 * The Product class represents a product offered by the bank.
 *
 * Each product has the following properties:
 * - productId: The unique identifier for the product (UUID)
 * - productName: The name of the product (String)
 * - productStatus: The status of the product (ProductStatus enum)
 * - productCurrencyCode: The currency code of the product (CurrencyCode enum)
 * - interestRate: The interest rate for the product (Double)
 * - productLimit: The limit of the product (Integer)
 * - createdAt: The timestamp when the product was created (LocalDateTime)
 * - updatedAt: The timestamp when the product was last updated (LocalDateTime)
 * - manager: The manager associated with the product (Manager)
 * - agreementList: The list of agreements associated with the product (List<Agreement>)
 *
 * The Agreement and Product classes are linked by a many-to-one relationship, where each agreement belongs to a product.
 *
 * The Agreement class overrides the equals(), hashCode(), and toString() methods to provide meaningful behavior
 * when comparing instances and converting them to string representation.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "agreements")
public class Agreement {

    /**
     * The unique identifier for an agreement.
     *
     * This field is annotated with @Id and represents the primary key of the Agreement entity.
     * It is mapped to the database column "agreement_id" and is generated using the UUID strategy.
     *
     * This field is private and can only be accessed within the Agreement class.
     *
     * @see Agreement
     * @see UUID
     * @see Id
     * @see Column
     * @see GeneratedValue
     * @see GenerationType
     */
    @Id
    @Column(name = "agreement_id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID agreementId;

    /**
     * Represents the interest rate associated with an agreement.
     *
     * <p>The interest rate is a percentage represented as a decimal value.
     * It is used to calculate the interest charged or earned on the agreement.
     *
     * <p>This value is stored in the database column "interest_rate".
     *
     * @see Agreement
     * @see Product
     */
    @Column(name = "interest_rate")
    private Double interestRate;

    /**
     * Represents the status of an agreement.
     *
     * The agreementStatus variable is an instance of the AgreementStatus enum,
     * which can have one of the following values:
     *
     * - ACTIVE: The agreement is currently active.
     * - PENDING: The agreement is pending and awaiting further action.
     * - EXPIRED: The agreement has expired and is no longer active.
     * - SUSPENDED: The agreement has been suspended and is temporarily inactive.
     * - CANCELED: The agreement has been canceled and is no longer valid.
     * - TERMINATED: The agreement has been terminated and is no longer valid.
     * - UNDER_REVIEW: The agreement is currently under review.
     * - DELETED: The agreement has been deleted and is no longer available.
     *
     * The agreementStatus variable is annotated with @Enumerated(EnumType.STRING) to
     * specify that the enum value should be stored as a string in the database.
     * It is also mapped to the "agreement_status" column in the database.
     *
     * This variable is a member of the Agreement class, which represents an agreement
     * between parties. The Agreement class has various other fields, including agreementId,
     * interestRate, agreementSum, createdAt, updatedAt, product, and account.
     *
     * The Agreement class has the following superclasses: [java.lang.Object]
     *
     * Example usage:
     * Agreement agreement = new Agreement();
     * agreement.setAgreementStatus(AgreementStatus.ACTIVE);
     *
     * System.out.println(agreement.getAgreementStatus()); // Output: ACTIVE
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "agreement_status")
    private AgreementStatus agreementStatus;

    /**
     * Represents the sum of an agreement.
     *
     * The agreementSum variable is used to store the total sum of an agreement. It is a double value and is annotated
     * with @Column to map it to a specific column in the database table "agreements". The name of the column is
     * "agreement_sum".
     *
     * Example usage:
     *     Agreement agreement = new Agreement();
     *     agreement.setAgreementSum(1000.0);
     *     Double agreementSum = agreement.getAgreementSum();
     *     System.out.println("Agreement sum: " + agreementSum);
     */
    @Column(name = "agreement_sum")
    private Double agreementSum;

    /**
     * Represents the timestamp when the object was created.
     *
     * <p>
     * This variable is annotated with:
     * - {@code @CreationTimestamp}: Marks the field to be populated with the creation timestamp of the record upon persisting.
     * - {@code @Temporal(TemporalType.TIMESTAMP)}: Specifies the type of temporal data stored in the field as TIMESTAMP.
     * - {@code @Column(name = "created_at", updatable = false)}: Specifies the name of the column in the database table and indicates that it is not updatable.
     * - {@code private LocalDateTime createdAt;}: Stores the creation timestamp as a {@code LocalDateTime} object.
     * </p>
     *
     * <p>
     * Example usage:
     * </p>
     * <pre>{@code
     * Product product = new Product();
     * LocalDateTime createdAt = product.getCreatedAt();
     * }</pre>
     *
     * @see CreationTimestamp
     * @see Temporal
     * @see TemporalType
     * @see Column
     * @see LocalDateTime
     */
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    /**
     * Represents the timestamp when the Agreement was last updated.
     * The value is automatically generated and updated by the system.
     *
     * The updatedAt variable is annotated with the following annotations:
     *
     * - @UpdateTimestamp: Marks the field to be updated with the current timestamp whenever the entity is modified.
     * - @Column(name = "updated_at"): Specifies the column name in the database table to which this field is mapped.
     * - @Temporal(TemporalType.TIMESTAMP): Specifies the type of temporal data to be stored in the database column.
     *   In this case, it indicates that the LocalDateTime value will be mapped to a SQL TIMESTAMP type.
     *
     * Example usage:
     *
     * Agreement agreement = new Agreement();
     * agreement.setUpdatedAt(LocalDateTime.now());
     *
     * System.out.println(agreement.getUpdatedAt()); // Output: 2021-10-15T15:30:45.123
     */
    @UpdateTimestamp
    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime updatedAt;

    /**
     * Represents a product.
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id", referencedColumnName = "product_id")
    private Product product;

    /**
     * Represents the associated Account for an Agreement.
     *
     * The Account is mapped to a foreign key column "account_id" in the Agreement table.
     * The fetch type is eagerly loaded for the Account.
     *
     * @see Agreement
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "account_id", referencedColumnName = "account_id")
    private Account account;

    /**
     * Compares this Agreement object to the specified object for equality.
     * Returns true if the specified object is also an Agreement object and
     * the agreementId of both objects are equal.
     *
     * @param o the object to compare to this Agreement
     * @return true if the specified object is equal to this Agreement, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Agreement agreement)) return false;
        return Objects.equals(agreementId, agreement.agreementId);
    }

    /**
     * Calculates the hash code for the Agreement object based on its agreementId.
     *
     * @return the hash code value for the Agreement object
     */
    @Override
    public int hashCode() {
        return Objects.hash(agreementId);
    }

    @Override
    public String toString() {
        return "Agreement{" +
                "agreementId=" + agreementId +
                ", interestRate=" + interestRate +
                ", agreementStatus=" + agreementStatus +
                ", agreementSum=" + agreementSum +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", product=" + product +
                ", account=" + account +
                '}';
    }
}
