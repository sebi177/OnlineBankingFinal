package com.example.onlinebankingfinal.model;

import com.example.onlinebankingfinal.model.enums.ClientStatus;
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
@Table(name = "clients")
public class Client {

    @Id
    @Column(name = "client_id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID clientId;

    @Enumerated(EnumType.STRING)
    @Column(name = "client_status")
    private ClientStatus clientStatus;

    @Column(name = "tax_code")
    private String taxCode;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "address")
    private String address;

    @Column(name = "phone")
    private String phone;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "client", fetch = FetchType.EAGER)
    private List<Account> accountList;

    @OneToMany(mappedBy = "client", fetch = FetchType.EAGER)
    private List<Card> cardList;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "manager_id", referencedColumnName = "manager_id")
    private Manager manager;

    public Client(String taxCode, String firstName, String lastName, String email, String address, String phone) {
        this.taxCode = taxCode;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.address = address;
        this.phone = phone;
    }
}

//              Client
//  +++      - id: UUID
//  +++      - status: ClientStatus
//  +++      - taxCode: String
//  +++      - firstName: String
//  +++      - lastName: String
//  +++      - email: String
//  +++      - address: String
//  +++      - phone : String
//  +++      - createdAt: Timestamp
//  +++      - updatedAt: Timestamp
//  +++      - accounts: List<Account>
//  +++      - manager: Manager
//        + equals(o: Object) : boolean
//        + hashCode() : int
//        + toString() : String
