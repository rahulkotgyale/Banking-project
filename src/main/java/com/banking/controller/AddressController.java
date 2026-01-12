package com.banking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.banking.entity.Address;
import com.banking.service.AddressService;

@RestController
@RequestMapping("/addresses")
public class AddressController {

    @Autowired
    private AddressService addressService;

    // CREATE with condition
    @PostMapping
    public Address create(@RequestBody Address address) {
        if (address.getCity() == null || address.getCity().isBlank()) {
            throw new RuntimeException("City is required");
        }
        if (address.getPincode() == null || address.getPincode().isBlank()) {
            throw new RuntimeException("Pincode is required");
        }
        return addressService.createAddress(address);
    }

    // READ ALL
    @GetMapping
    public List<Address> getAll() {
        return addressService.getAllAddresses();
    }

    // READ BY ID with condition
    @GetMapping("/{id}")
    public Address getById(@PathVariable Long id) {
        if (id <= 0) {
            throw new RuntimeException("Invalid address id");
        }
        return addressService.getAddressById(id);
    }

    // UPDATE with condition
    @PutMapping("/{id}")
    public Address update(@PathVariable Long id, @RequestBody Address address) {
        if (id <= 0) {
            throw new RuntimeException("Invalid address id");
        }
        if (address.getCity() != null && address.getCity().isBlank()) {
            throw new RuntimeException("City cannot be empty");
        }
        return addressService.updateAddress(id, address);
    }

    // DELETE with condition
    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        if (id <= 0) {
            throw new RuntimeException("Invalid address id");
        }
        addressService.deleteAddress(id);
        return "Address deleted successfully";
    }
}
