package com.banking.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import com.banking.entity.ATMAccount;
import com.banking.service.ATMAccountService;

@RestController
@RequestMapping("/atm")
public class ATMAccountController {

    @Autowired
    private ATMAccountService service;

    // Create ATM Account with conditions
    @PostMapping("/create")
    public ResponseEntity<ATMAccount> create(@RequestBody ATMAccount req) {

        if (req.getCardNumber() == null || req.getCardNumber().isBlank()) {
            throw new RuntimeException("Card number is required");
        }
        if (req.getPin() == null || req.getPin().isBlank()) {
            throw new RuntimeException("PIN is required");
        }
        if (req.getBalance() != null && req.getBalance() < 0) {
            throw new RuntimeException("Balance cannot be negative");
        }
        if (req.getHolderName() == null || req.getHolderName().isBlank()) {
            throw new RuntimeException("Holder name is required");
        }

        ATMAccount acc = new ATMAccount();
        acc.setCardNumber(req.getCardNumber());
        acc.setPin(req.getPin());
        acc.setBalance(req.getBalance() == null ? 0.0 : req.getBalance());
        acc.setHolderName(req.getHolderName());

        return new ResponseEntity<>(service.createAccount(acc), HttpStatus.CREATED);
    }

    // Get All Accounts
    @GetMapping("/all")
    public ResponseEntity<List<ATMAccount>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    // Get By Id with condition
    @GetMapping("/{id}")
    public ResponseEntity<ATMAccount> getById(@PathVariable Long id) {
        if (id <= 0) {
            throw new RuntimeException("Invalid account id");
        }
        return ResponseEntity.ok(service.getById(id));
    }

    // Get By Card Number with condition
    @GetMapping("/card/{cardNumber}")
    public ResponseEntity<ATMAccount> getByCard(@PathVariable String cardNumber) {
        if (cardNumber == null || cardNumber.isBlank()) {
            throw new RuntimeException("Card number is required");
        }
        return ResponseEntity.ok(service.getByCard(cardNumber));
    }

    // Update with conditions
    @PutMapping("/update/{id}")
    public ResponseEntity<ATMAccount> update(
            @PathVariable Long id,
            @RequestBody ATMAccount req) {

        if (id <= 0) {
            throw new RuntimeException("Invalid account id");
        }
        if (req.getBalance() != null && req.getBalance() < 0) {
            throw new RuntimeException("Balance cannot be negative");
        }
        if (req.getPin() != null && req.getPin().isBlank()) {
            throw new RuntimeException("PIN cannot be empty");
        }

        ATMAccount acc = new ATMAccount();
        acc.setCardNumber(req.getCardNumber());
        acc.setPin(req.getPin());
        acc.setBalance(req.getBalance());
        acc.setHolderName(req.getHolderName());

        return ResponseEntity.ok(service.update(id, acc));
    }

    // Delete with condition
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        if (id <= 0) {
            throw new RuntimeException("Invalid account id");
        }
        service.delete(id);
        return ResponseEntity.ok("Account deleted successfully");
    }
}
