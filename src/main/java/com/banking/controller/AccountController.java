package com.banking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.banking.entity.Account;
import com.banking.service.AccountService;

@RestController
@RequestMapping("/accounts")
public class AccountController {

	@Autowired
    private AccountService accountService;

    // Constructor Injection (Best Practice)
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    // ================= CREATE ACCOUNT =================
    // POST /accounts/{customerId}
    @PostMapping("/{customerId}")
    public ResponseEntity<Account> createAccount(
            @PathVariable Long customerId,
            @RequestBody Account account) {

        Account createdAccount = accountService.createAccount(customerId, account);
        return new ResponseEntity<>(createdAccount, HttpStatus.CREATED);
    }

    // ================= GET ALL ACCOUNTS =================
    // GET /accounts
    @GetMapping("/getAllAccount")
    public ResponseEntity<List<Account>> getAllAccounts() {
        return ResponseEntity.ok(accountService.getAllAccounts());
    }

    // ================= GET ACCOUNT BY ID =================
    // GET /accounts/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Account> getAccountById(@PathVariable Long id) {
        return ResponseEntity.ok(accountService.getAccountById(id));
    }

    // ================= UPDATE ACCOUNT =================
    // PUT /accounts/{id}
    @PutMapping("/{id}")
    public ResponseEntity<Account> updateAccount(
            @PathVariable Long id,
            @RequestBody Account account) {

        return ResponseEntity.ok(accountService.updateAccount(id, account));
    }

    // ================= DELETE ACCOUNT =================
    // DELETE /accounts/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAccount(@PathVariable Long id) {
        accountService.deleteAccount(id);
        return ResponseEntity.noContent().build();
    }
}
