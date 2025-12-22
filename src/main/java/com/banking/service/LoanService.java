package com.banking.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import com.banking.Repository.LoanRepository;
import com.banking.entity.Loan;

@Service
public class LoanService {

	@Autowired
    private LoanRepository loanRepository;

    // CREATE
    public Loan create(Loan loan) {
        try {
            loan.setCreatedDate(LocalDate.now());
            return loanRepository.save(loan);
        } catch (Exception e) {
            throw new RuntimeException("Error while creating loan", e);
        }
    }

    // READ ALL
    public List<Loan> getAll() {
        try {
            return loanRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Error while fetching loans", e);
        }
    }

    // READ BY ID
    public Loan getById(Long id) {
        try {
            return loanRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Loan not found with id: " + id));
        } catch (Exception e) {
            throw new RuntimeException("Error while fetching loan", e);
        }
    }

    // UPDATE
    public Loan update(Long id, Loan loan) {
        try {
            Loan existing = getById(id);

            existing.setLoanType(loan.getLoanType());
            existing.setLoanAmount(loan.getLoanAmount());
            existing.setInterestRate(loan.getInterestRate());
            existing.setTenureMonths(loan.getTenureMonths());
            existing.setStatus(loan.getStatus());
            existing.setCustomer(loan.getCustomer());

            return loanRepository.save(existing);
        } catch (Exception e) {
            throw new RuntimeException("Error while updating loan", e);
        }
    }

    // DELETE
    public void delete(Long id) {
        try {
            loanRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Error while deleting loan", e);
        }
    }
}
