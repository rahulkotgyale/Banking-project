package com.banking.entity;

import java.time.LocalDate;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "loans")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long loanId;

    private String loanType;       // HOME / PERSONAL
    private Double loanAmount;
    private Double interestRate;
    private Integer tenureMonths;
    private String status;         // APPROVED / PENDING
    private LocalDate createdDate;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
}
