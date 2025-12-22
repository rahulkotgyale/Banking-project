package com.banking.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.banking.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
