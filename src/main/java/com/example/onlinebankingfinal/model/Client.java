package com.example.onlinebankingfinal.model;

import com.example.onlinebankingfinal.model.enums.ClientStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
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
    @Column(name = "created_at", updatable = false)
    private Timestamp createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Timestamp updatedAt;

    @OneToMany(mappedBy = "client", fetch = FetchType.EAGER)
    private List<Account> accountList;

    @OneToMany(mappedBy = "client", fetch = FetchType.EAGER)
    private List<Card> cardList;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "manager_id", referencedColumnName = "manager_id")
    private Manager manager;


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
