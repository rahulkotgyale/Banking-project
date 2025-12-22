package com.banking.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banking.Repository.AccountRepository;
import com.banking.Repository.TransactionRepository;
import com.banking.entity.Account;
import com.banking.entity.Transaction;

import jakarta.persistence.EntityNotFoundException;

@Service
public class TransactionService {

	@Autowired
    private TransactionRepository transactionRepository;
	@Autowired
    private AccountRepository accountRepository;

    // Constructor Injection
    public TransactionService(TransactionRepository transactionRepository,
                              AccountRepository accountRepository) {
        this.transactionRepository = transactionRepository;
        this.accountRepository = accountRepository;
    }

    // ================= CREATE TRANSACTION (DEBIT / CREDIT) =================
    public Transaction createTransaction(Long accountId, Transaction transaction) {

        Account account = accountRepository.findById(accountId)
                .orElseThrow(() ->
                        new EntityNotFoundException("Account not found with id: " + accountId));

        // Set common fields
        transaction.setAccount(account);
        transaction.setTransactionDate(LocalDateTime.now());

        // CREDIT logic
        if ("CREDIT".equalsIgnoreCase(transaction.getTransactionType())) {
            account.setBalance(account.getBalance() + transaction.getAmount());
        }
        // DEBIT logic
        else if ("DEBIT".equalsIgnoreCase(transaction.getTransactionType())) {

            if (account.getBalance() < transaction.getAmount()) {
                throw new RuntimeException("Insufficient balance");
            }

            account.setBalance(account.getBalance() - transaction.getAmount());
        }
        else {
            throw new RuntimeException("Invalid transaction type");
        }

        // Save updated account & transaction
        accountRepository.save(account);
        return transactionRepository.save(transaction);
    }

    // ================= GET ALL TRANSACTIONS =================
    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    // ================= GET TRANSACTION BY ID =================
    public Transaction getTransactionById(Long id) {
        return transactionRepository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException("Transaction not found with id: " + id));
    }

    // ================= DELETE TRANSACTION =================
    public void deleteTransaction(Long id) {
        Transaction transaction = getTransactionById(id);
        transactionRepository.delete(transaction);
    }
}
