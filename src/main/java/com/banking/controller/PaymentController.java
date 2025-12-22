package com.banking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;
import com.banking.entity.Account;
import com.banking.entity.Payment;
import com.banking.service.PaymentService;

@RestController
@RequestMapping("/payments")
public class PaymentController {

	@Autowired
    private PaymentService paymentService;

    // CREATE
    @PostMapping
    public ResponseEntity<?> create(@RequestBody Payment request) {
        try {
            Payment payment = new Payment();
            payment.setPaymentType(request.getPaymentType());
            payment.setAmount(request.getAmount());
            payment.setStatus(request.getStatus());

            Account account = new Account();
            account.setAccountId(request.getAccount().getAccountId());
            payment.setAccount(account);

            return new ResponseEntity<>(paymentService.create(payment), HttpStatus.CREATED);

        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // READ ALL
    @GetMapping
    public ResponseEntity<?> getAll() {
        try {
            List<Payment> payments = paymentService.getAll();
            return new ResponseEntity<>(payments, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // READ BY ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(paymentService.getById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Payment request) {
        try {
            Payment payment = new Payment();
            payment.setPaymentType(request.getPaymentType());
            payment.setAmount(request.getAmount());
            payment.setStatus(request.getStatus());

            Account account = new Account();
            account.setAccountId(request.getAccount().getAccountId());
            payment.setAccount(account);

            return new ResponseEntity<>(paymentService.update(id, payment), HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            paymentService.delete(id);
            return new ResponseEntity<>("Payment deleted successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
