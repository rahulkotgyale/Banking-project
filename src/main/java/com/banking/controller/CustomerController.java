package com.banking.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.banking.entity.Customer;
import com.banking.service.CustomerService;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    // ================= CREATE CUSTOMER =================
    @PostMapping("/addCustomer")
    public ResponseEntity<?> addCustomer(@RequestBody Customer customer) {
        try {
            if (customer.getName() == null || customer.getName().isBlank()) {
                return new ResponseEntity<>("Name is required", HttpStatus.BAD_REQUEST);
            }
            if (customer.getEmail() == null || customer.getEmail().isBlank()) {
                return new ResponseEntity<>("Email is required", HttpStatus.BAD_REQUEST);
            }
            if (customer.getMobile() == null || customer.getMobile().isBlank()) {
                return new ResponseEntity<>("Mobile is required", HttpStatus.BAD_REQUEST);
            }

            Customer savedCustomer = customerService.createCustomer(customer);

            Map<String, Object> response = new HashMap<>();
            response.put("id", savedCustomer.getId());
            response.put("name", savedCustomer.getName());
            response.put("email", savedCustomer.getEmail());
            response.put("mobile", savedCustomer.getMobile());
            response.put("address", savedCustomer.getAddress());

            return new ResponseEntity<>(response, HttpStatus.CREATED);

        } catch (Exception e) {
            return new ResponseEntity<>("Error while adding customer", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // ================= GET ALL CUSTOMERS =================
    @GetMapping("/getAllCustomer")
    public ResponseEntity<?> getAllCustomers() {
        try {
            List<Customer> customers = customerService.getAllCustomers();
            return new ResponseEntity<>(customers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error while fetching customers", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // ================= GET CUSTOMER BY ID =================
    @GetMapping("/getCustomer/{id}")
    public ResponseEntity<?> getCustomerById(@PathVariable Long id) {
        try {
            if (id <= 0) {
                return new ResponseEntity<>("Invalid customer id", HttpStatus.BAD_REQUEST);
            }

            Customer customer = customerService.getCustomerById(id);

            Map<String, Object> response = new HashMap<>();
            response.put("id", customer.getId());
            response.put("name", customer.getName());
            response.put("email", customer.getEmail());
            response.put("mobile", customer.getMobile());
            response.put("address", customer.getAddress());

            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>("Customer not found with id: " + id, HttpStatus.NOT_FOUND);
        }
    }

    // ================= UPDATE CUSTOMER =================
    @PutMapping("/updateCustomer/{id}")
    public ResponseEntity<?> updateCustomer(
            @PathVariable Long id,
            @RequestBody Customer customer) {
        try {
            if (id <= 0) {
                return new ResponseEntity<>("Invalid customer id", HttpStatus.BAD_REQUEST);
            }
            if (customer.getName() != null && customer.getName().isBlank()) {
                return new ResponseEntity<>("Name cannot be empty", HttpStatus.BAD_REQUEST);
            }
            if (customer.getEmail() != null && customer.getEmail().isBlank()) {
                return new ResponseEntity<>("Email cannot be empty", HttpStatus.BAD_REQUEST);
            }

            Customer updatedCustomer = customerService.updateCustomer(id, customer);

            Map<String, Object> response = new HashMap<>();
            response.put("id", updatedCustomer.getId());
            response.put("name", updatedCustomer.getName());
            response.put("email", updatedCustomer.getEmail());
            response.put("mobile", updatedCustomer.getMobile());
            response.put("address", updatedCustomer.getAddress());

            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>("Error while updating customer", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // ================= DELETE CUSTOMER =================
    @DeleteMapping("/deleteCustomer/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable Long id) {
        try {
            if (id <= 0) {
                return new ResponseEntity<>("Invalid customer id", HttpStatus.BAD_REQUEST);
            }

            Customer customer = customerService.getCustomerById(id);
            customerService.deleteCustomer(id);

            Map<String, Object> response = new HashMap<>();
            response.put("id", customer.getId());
            response.put("name", customer.getName());
            response.put("email", customer.getEmail());
            response.put("mobile", customer.getMobile());
            response.put("address", customer.getAddress());

            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>("Error while deleting customer", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
