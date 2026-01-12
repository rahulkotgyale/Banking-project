package com.banking.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banking.Repository.ATMAccountRepository;
import com.banking.entity.ATMAccount;

@Service
public class ATMAccountService {

    @Autowired
    private ATMAccountRepository repo;

    // Create with condition
    public ATMAccount createAccount(ATMAccount acc) {
        if (repo.findByCardNumber(acc.getCardNumber()).isPresent()) {
            throw new RuntimeException("Card number already exists");
        }
        if (acc.getBalance() == null || acc.getBalance() < 0) {
            acc.setBalance(0.0);
        }
        return repo.save(acc);
    }

    // Read All
    public List<ATMAccount> getAll() {
        return repo.findAll();
    }

    // Read By Id
    public ATMAccount getById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Account not found with id " + id));
    }

    // Read By Card
    public ATMAccount getByCard(String cardNumber) {
        return repo.findByCardNumber(cardNumber)
                .orElseThrow(() -> new RuntimeException("Card not found"));
    }

    // Update with conditions
    public ATMAccount update(Long id, ATMAccount data) {
        ATMAccount acc = getById(id);

        if (data.getPin() != null && !data.getPin().isBlank()) {
            acc.setPin(data.getPin());
        }

        if (data.getHolderName() != null && !data.getHolderName().isBlank()) {
            acc.setHolderName(data.getHolderName());
        }

        if (data.getBalance() != null) {
            if (data.getBalance() < 0) {
                throw new RuntimeException("Balance cannot be negative");
            }
            acc.setBalance(data.getBalance());
        }

        return repo.save(acc);
    }

    // Delete with check
    public void delete(Long id) {
        if (!repo.existsById(id)) {
            throw new RuntimeException("Account not found with id " + id);
        }
        repo.deleteById(id);
    }
}
