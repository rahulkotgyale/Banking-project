package com.banking.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import com.banking.entity.*;
import com.banking.service.AccountService;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    // CREATE ACCOUNT with conditions
    @PostMapping("/{customerId}")
    public ResponseEntity<Account> createAccount(
            @PathVariable Long customerId,
            @RequestBody Account req) {

        if (customerId <= 0) {
            throw new RuntimeException("Invalid customer id");
        }
        if (req.getAccountNumber() == null || req.getAccountNumber().isBlank()) {
            throw new RuntimeException("Account number is required");
        }
        if (req.getAccountType() == null || req.getAccountType().isBlank()) {
            throw new RuntimeException("Account type is required");
        }
        if (req.getBalance() == null || req.getBalance() < 0) {
            req.setBalance(0.0);
        }

        Account account = new Account();
        account.setAccountNumber(req.getAccountNumber());
        account.setAccountType(req.getAccountType());
        account.setBalance(req.getBalance());

        return new ResponseEntity<>(
                accountService.createAccount(customerId, account),
                HttpStatus.CREATED
        );
    }

    // GET ALL
    @GetMapping("/getAllAccount")
    public ResponseEntity<List<Account>> getAllAccounts() {
        return ResponseEntity.ok(accountService.getAllAccounts());
    }

    // GET BY ID with condition
    @GetMapping("/{id}")
    public ResponseEntity<Account> getAccountById(@PathVariable Long id) {
        if (id <= 0) {
            throw new RuntimeException("Invalid account id");
        }
        return ResponseEntity.ok(accountService.getAccountById(id));
    }

    // UPDATE with condition
    @PutMapping("/{id}")
    public ResponseEntity<Account> updateAccount(
            @PathVariable Long id,
            @RequestBody Account req) {

        if (id <= 0) {
            throw new RuntimeException("Invalid account id");
        }
        if (req.getBalance() != null && req.getBalance() < 0) {
            throw new RuntimeException("Balance cannot be negative");
        }

        return ResponseEntity.ok(accountService.updateAccount(id, req));
    }

    // DELETE with condition
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAccount(@PathVariable Long id) {
        if (id <= 0) {
            throw new RuntimeException("Invalid account id");
        }
        accountService.deleteAccount(id);
        return ResponseEntity.noContent().build();
    }
}
