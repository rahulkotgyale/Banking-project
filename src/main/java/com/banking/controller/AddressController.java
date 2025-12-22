package com.banking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;
import com.banking.entity.Address;
import com.banking.service.AddressService;

@RestController
@RequestMapping("/addresses")
public class AddressController {

	@Autowired
    private AddressService addressService;

    // CREATE
    @PostMapping
    public Address create(@RequestBody Address address) {
        return addressService.createAddress(address);
    }

    // READ ALL
    @GetMapping
    public List<Address> getAll() {
        return addressService.getAllAddresses();
    }

    // READ BY ID
    @GetMapping("/{id}")
    public Address getById(@PathVariable Long id) {
        return addressService.getAddressById(id);
    }

    // UPDATE
    @PutMapping("/{id}")
    public Address update(@PathVariable Long id, @RequestBody Address address) {
        return addressService.updateAddress(id, address);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        addressService.deleteAddress(id);
        return "Address deleted successfully";
    }
}
