package com.banking.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banking.Repository.RegistrationRepository;
import com.banking.entity.Registration;

@Service
public class RegistrationService {

    @Autowired
    private RegistrationRepository registrationRepository;

    // CREATE
    public Registration registerUser(Registration registration) {
        try {
            return registrationRepository.save(registration);
        } catch (Exception e) {
            throw new RuntimeException("Error while registering user");
        }
    }

    // READ ALL
    public List<Registration> getAllUsers() {
        try {
            return registrationRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Error while fetching users");
        }
    }

    // READ BY ID
    public Registration getUserById(Long id) {
        try {
            return registrationRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("User not found"));
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    // UPDATE
    public Registration updateUser(Long id, Registration registration) {
        try {
            Registration existing = getUserById(id);
            existing.setName(registration.getName());
            existing.setEmail(registration.getEmail());
            existing.setMobile(registration.getMobile());
            existing.setPassword(registration.getPassword());
            return registrationRepository.save(existing);
        } catch (Exception e) {
            throw new RuntimeException("Error while updating user");
        }
    }

    // DELETE
    public void deleteUser(Long id) {
        try {
            Registration user = getUserById(id);
            registrationRepository.delete(user);
        } catch (Exception e) {
            throw new RuntimeException("Error while deleting user");
        }
    }

    // 🔐 LOGIN
    public Registration login(String email, String password) {
        try {
            return registrationRepository
                    .findByEmailAndPassword(email, password)
                    .orElseThrow(() -> new RuntimeException("Invalid email or password"));
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
