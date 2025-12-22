package com.banking.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.banking.entity.Transaction;
import com.banking.service.TransactionService;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    // Constructor Injection
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    // ================= CREATE TRANSACTION =================
    // POST /transactions/{accountId}
    @PostMapping("/{accountId}")
    public ResponseEntity<Transaction> createTransaction(
            @PathVariable Long accountId,
            @RequestBody Transaction transaction) {

        return new ResponseEntity<>(
                transactionService.createTransaction(accountId, transaction),
                HttpStatus.CREATED
        );
    }

    // ================= GET ALL TRANSACTIONS =================
    // GET /transactions
    @GetMapping
    public ResponseEntity<List<Transaction>> getAllTransactions() {
        return ResponseEntity.ok(transactionService.getAllTransactions());
    }

    // ================= GET TRANSACTION BY ID =================
    // GET /transactions/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Transaction> getTransactionById(@PathVariable Long id) {
        return ResponseEntity.ok(transactionService.getTransactionById(id));
    }

    // ================= DELETE TRANSACTION =================
    // DELETE /transactions/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTransaction(@PathVariable Long id) {
        transactionService.deleteTransaction(id);
        return ResponseEntity.noContent().build();
    }
}
