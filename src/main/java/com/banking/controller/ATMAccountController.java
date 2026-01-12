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

    // Create ATM Account (set all fields)
    @PostMapping("/create")
    public ResponseEntity<ATMAccount> create(@RequestBody ATMAccount req) {
        ATMAccount acc = new ATMAccount();
        acc.setCardNumber(req.getCardNumber());
        acc.setPin(req.getPin());
        acc.setBalance(req.getBalance());
        acc.setHolderName(req.getHolderName());

        return new ResponseEntity<>(service.createAccount(acc), HttpStatus.CREATED);
    }

    // Get All Accounts
    @GetMapping("/all")
    public ResponseEntity<List<ATMAccount>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    // Get By Id
    @GetMapping("/{id}")
    public ResponseEntity<ATMAccount> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    // Get By Card Number
    @GetMapping("/card/{cardNumber}")
    public ResponseEntity<ATMAccount> getByCard(@PathVariable String cardNumber) {
        return ResponseEntity.ok(service.getByCard(cardNumber));
    }

    // Update All Fields
    @PutMapping("/update/{id}")
    public ResponseEntity<ATMAccount> update(
            @PathVariable Long id,
            @RequestBody ATMAccount req) {

        ATMAccount acc = new ATMAccount();
        acc.setCardNumber(req.getCardNumber());
        acc.setPin(req.getPin());
        acc.setBalance(req.getBalance());
        acc.setHolderName(req.getHolderName());

        return ResponseEntity.ok(service.update(id, acc));
    }

    // Delete Account
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok("Account deleted successfully");
    }
}
