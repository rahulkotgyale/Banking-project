package com.banking.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import com.banking.Repository.AddressRepository;
import com.banking.entity.Address;

@Service
public class AddressService {

	@Autowired
    private AddressRepository addressRepository;

    // CREATE
    public Address createAddress(Address address) {
        return addressRepository.save(address);
    }

    // READ ALL
    public List<Address> getAllAddresses() {
        return addressRepository.findAll();
    }

    // READ BY ID
    public Address getAddressById(Long id) {
        return addressRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Address not found"));
    }

    // UPDATE
    public Address updateAddress(Long id, Address address) {
        Address existing = getAddressById(id);

        existing.setAddressLine(address.getAddressLine());
        existing.setCity(address.getCity());
        existing.setState(address.getState());
        existing.setCountry(address.getCountry());
        existing.setPincode(address.getPincode());

        return addressRepository.save(existing);
    }

    // DELETE
    public void deleteAddress(Long id) {
        addressRepository.deleteById(id);
    }
}
