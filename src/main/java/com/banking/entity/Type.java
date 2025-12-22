package com.banking.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "types")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Type {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long typeId;

    private String typeCategory;  // ACCOUNT_TYPE, LOAN_TYPE, CARD_TYPE
    private String typeValue;     // SAVINGS, CURRENT, HOME_LOAN
    private boolean active;
}
