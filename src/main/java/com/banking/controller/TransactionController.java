package com.banking.controller;

import java.util.List;

import org.springframework.http.*;
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
    @PostMapping("/{accountId}")
    public ResponseEntity<?> createTransaction(
            @PathVariable Long accountId,
            @RequestBody Transaction transaction) {
        try {
            return new ResponseEntity<>(
                    transactionService.createTransaction(accountId, transaction),
                    HttpStatus.CREATED
            );
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // ================= GET ALL TRANSACTIONS =================
    @GetMapping
    public ResponseEntity<?> getAllTransactions() {
        try {
            List<Transaction> list = transactionService.getAllTransactions();
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // ================= GET TRANSACTION BY ID =================
    @GetMapping("/{id}")
    public ResponseEntity<?> getTransactionById(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(transactionService.getTransactionById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    // ================= DELETE TRANSACTION =================
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTransaction(@PathVariable Long id) {
        try {
            transactionService.deleteTransaction(id);
            return new ResponseEntity<>("Transaction deleted successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
