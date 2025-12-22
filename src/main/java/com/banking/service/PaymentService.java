package com.banking.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import com.banking.Repository.PaymentRepository;
import com.banking.entity.Payment;

@Service
public class PaymentService {

	@Autowired
    private PaymentRepository paymentRepository;

    // CREATE
    public Payment create(Payment payment) {
        try {
            payment.setPaymentDate(LocalDateTime.now());
            return paymentRepository.save(payment);
        } catch (Exception e) {
            throw new RuntimeException("Error while creating payment", e);
        }
    }

    // READ ALL
    public List<Payment> getAll() {
        try {
            return paymentRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Error while fetching payments", e);
        }
    }

    // READ BY ID
    public Payment getById(Long id) {
        try {
            return paymentRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Payment not found with id: " + id));
        } catch (Exception e) {
            throw new RuntimeException("Error while fetching payment", e);
        }
    }

    // UPDATE
    public Payment update(Long id, Payment payment) {
        try {
            Payment existing = getById(id);

            existing.setPaymentType(payment.getPaymentType());
            existing.setAmount(payment.getAmount());
            existing.setStatus(payment.getStatus());
            existing.setAccount(payment.getAccount());

            return paymentRepository.save(existing);
        } catch (Exception e) {
            throw new RuntimeException("Error while updating payment", e);
        }
    }

    // DELETE
    public void delete(Long id) {
        try {
            paymentRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Error while deleting payment", e);
        }
    }
}
