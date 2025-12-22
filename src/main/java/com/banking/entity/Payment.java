package com.banking.entity;

import java.time.LocalDateTime;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "payments")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paymentId;

    private String paymentType;      // UPI, NEFT, IMPS
    private Double amount;
    private String status;           // SUCCESS, FAILED
    private LocalDateTime paymentDate;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;
}
