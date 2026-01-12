package com.banking.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "atm_account")
public class ATMAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String cardNumber;

    @Column(nullable = false)
    private String pin;

    private Double balance;

    private String holderName;

    // Constructors
    public ATMAccount() {}

    public ATMAccount(String cardNumber, String pin, Double balance, String holderName) {
        this.cardNumber = cardNumber;
        this.pin = pin;
        this.balance = balance;
        this.holderName = holderName;
    }

    // Getters and Setters
    public Long getId() { return id; }

    public String getCardNumber() { return cardNumber; }
    public void setCardNumber(String cardNumber) { this.cardNumber = cardNumber; }

    public String getPin() { return pin; }
    public void setPin(String pin) { this.pin = pin; }

    public Double getBalance() { return balance; }
    public void setBalance(Double balance) { this.balance = balance; }

    public String getHolderName() { return holderName; }
    public void setHolderName(String holderName) { this.holderName = holderName; }
}
