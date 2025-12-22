package com.banking.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.banking.entity.Loan;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Long> {

}
