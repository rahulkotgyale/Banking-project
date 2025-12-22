package com.banking.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.banking.entity.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {

}
