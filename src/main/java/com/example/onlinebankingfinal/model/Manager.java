package com.example.onlinebankingfinal.model;

import com.example.onlinebankingfinal.model.enums.ManagerStatus;
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
@Table(name = "managers")
public class Manager {

    @Id
    @Column(name = "manager_id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID managerId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Enumerated(EnumType.STRING)
    @Column(name = "manager_status")
    private ManagerStatus managerStatus;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "manager", fetch = FetchType.EAGER)
    private List<Client> clientList;

    @OneToMany(mappedBy = "manager", fetch = FetchType.EAGER)
    private List<Product> productList;

}

//          Manager
//        - id: UUID
//        - firstName: String
//        - lastName: String
//        - status: ManagerStatus
//        - createdAt: Timestamp
//        - updatedAt: Timestamp
//        - clients: List<Client>
//        - products: List<Product>
//        + equals(o: Object) : boolean
//        + hashCode(): int
//        + toString() : String

