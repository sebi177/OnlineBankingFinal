package com.example.onlinebankingfinal.model;

import com.example.onlinebankingfinal.model.enums.CardType;
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
@Table(name = "cards")
public class Card {

    @Id
    @Column(name = "card_id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID cardId;

    @Enumerated(EnumType.STRING)
    @Column(name = "card_type")
    private CardType cardType;

    @Column(name = "card_number")
    private String cardNumber;

    @Column(name = "card_holder")
    private String cardHolder;

    @Column(name = "cvv")
    private Integer cvv;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "expiration_date")
    private LocalDateTime expirationDate;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @OneToOne(mappedBy = "card", fetch = FetchType.EAGER)
    private Account account;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "client_id", referencedColumnName = "client_id")
    private Client client;
}
