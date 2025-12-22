package com.banking.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banking.Repository.AccountRepository;
import com.banking.Repository.CustomerRepository;
import com.banking.entity.Account;
import com.banking.entity.Customer;

import jakarta.persistence.EntityNotFoundException;

@Service
public class AccountService {

	@Autowired
    private AccountRepository accountRepository;
	
	@Autowired
    private CustomerRepository customerRepository;

    // Constructor Injection (Best Practice)
    public AccountService(AccountRepository accountRepository,
                          CustomerRepository customerRepository) {
        this.accountRepository = accountRepository;
        this.customerRepository = customerRepository;
    }

    // ================= CREATE ACCOUNT =================
    public Account createAccount(Long customerId, Account account) {

        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() ->
                        new EntityNotFoundException("Customer not found with id: " + customerId));

        account.setCustomer(customer);
        return accountRepository.save(account);
    }

    // ================= GET ALL ACCOUNTS =================
    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    // ================= GET ACCOUNT BY ID =================
    public Account getAccountById(Long id) {
        return accountRepository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException("Account not found with id: " + id));
    }

    // ================= UPDATE ACCOUNT =================
    public Account updateAccount(Long id, Account account) {

        Account existing = getAccountById(id);

        existing.setAccountNumber(account.getAccountNumber());
        existing.setAccountType(account.getAccountType());
        existing.setBalance(account.getBalance());

        return accountRepository.save(existing);
    }

    // ================= DELETE ACCOUNT =================
    public void deleteAccount(Long id) {
        Account account = getAccountById(id);
        accountRepository.delete(account);
    }
}
