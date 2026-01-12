package com.banking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import com.banking.entity.Loan;
import com.banking.service.LoanService;

@RestController
@RequestMapping("/loans")
public class LoanController {

    @Autowired
    private LoanService loanService;

    // CREATE with conditions
    @PostMapping
    public ResponseEntity<?> create(@RequestBody Loan loan) {
        try {
            if (loan.getLoanType() == null || loan.getLoanType().isBlank()) {
                return new ResponseEntity<>("Loan type is required", HttpStatus.BAD_REQUEST);
            }

            if (loan.getInterestRate() == null || loan.getInterestRate() <= 0) {
                return new ResponseEntity<>("Interest rate must be greater than 0", HttpStatus.BAD_REQUEST);
            }

            return new ResponseEntity<>(loanService.create(loan), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // READ ALL
    @GetMapping
    public ResponseEntity<?> getAll() {
        try {
            List<Loan> loans = loanService.getAll();
            return new ResponseEntity<>(loans, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // READ BY ID with condition
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        try {
            if (id <= 0) {
                return new ResponseEntity<>("Invalid loan id", HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(loanService.getById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    // UPDATE with conditions
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Loan loan) {
        try {
            if (id <= 0) {
                return new ResponseEntity<>("Invalid loan id", HttpStatus.BAD_REQUEST);
            }

            if (loan.getInterestRate() != null && loan.getInterestRate() <= 0) {
                return new ResponseEntity<>("Interest rate must be greater than 0", HttpStatus.BAD_REQUEST);
            }

            return new ResponseEntity<>(loanService.update(id, loan), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // DELETE with condition
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            if (id <= 0) {
                return new ResponseEntity<>("Invalid loan id", HttpStatus.BAD_REQUEST);
            }
            loanService.delete(id);
            return new ResponseEntity<>("Loan deleted successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
