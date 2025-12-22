package com.banking.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "nominees")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Nominee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long nomineeId;

    private String nomineeName;
    private String relationship;
    private Integer age;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;
}
