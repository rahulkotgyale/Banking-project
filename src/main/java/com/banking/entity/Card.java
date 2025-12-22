package com.banking.entity;

import java.time.LocalDate;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "cards")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cardId;

    @Column(unique = true)
    private String cardNumber;

    private String cardType;   // DEBIT / CREDIT
    private LocalDate expiryDate;
    private Integer cvv;
    private String status;     // ACTIVE / BLOCKED

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;
}
