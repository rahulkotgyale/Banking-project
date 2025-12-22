package com.banking.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banking.Repository.CustomerRepository;
import com.banking.entity.Customer;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    // ================= CREATE CUSTOMER =================
    public Customer createCustomer(Customer customer) {
        try {
            return customerRepository.save(customer);
        } catch (Exception e) {
            throw new RuntimeException("Error while saving customer");
        }
    }

    // ================= GET ALL CUSTOMERS =================
    public List<Customer> getAllCustomers() {
        try {
            return customerRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Error while fetching customers");
        }
    }

    // ================= GET CUSTOMER BY ID =================
    public Customer getCustomerById(Long id) {
        try {
            return customerRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Customer not found with id: " + id));
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    // ================= UPDATE CUSTOMER =================
    public Customer updateCustomer(Long id, Customer customer) {
        try {
            Customer existing = getCustomerById(id);

            // updating all fields
            existing.setName(customer.getName());
            existing.setEmail(customer.getEmail());
            existing.setMobile(customer.getMobile());
            existing.setAddress(customer.getAddress());

            return customerRepository.save(existing);

        } catch (Exception e) {
            throw new RuntimeException("Error while updating customer");
        }
    }

    // ================= DELETE CUSTOMER =================
    public void deleteCustomer(Long id) {
        try {
            Customer customer = getCustomerById(id);
            customerRepository.delete(customer);
        } catch (Exception e) {
            throw new RuntimeException("Error while deleting customer");
        }
    }
}
