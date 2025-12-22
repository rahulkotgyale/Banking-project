package com.banking.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.banking.Repository.UserRepository;
import com.banking.entity.User;

@Service
public class UserService {

	@Autowired
    private UserRepository userRepository;

    // CREATE
    public User create(User user) {
        try {
            return userRepository.save(user);
        } catch (Exception e) {
            throw new RuntimeException("Error while creating user", e);
        }
    }

    // READ ALL
    public List<User> getAll() {
        try {
            return userRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Error while fetching users", e);
        }
    }

    // READ BY ID
    public User getById(Long id) {
        try {
            return userRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
        } catch (Exception e) {
            throw new RuntimeException("Error while fetching user", e);
        }
    }

    // UPDATE
    public User update(Long id, User user) {
        try {
            User existing = getById(id);

            existing.setUsername(user.getUsername());
            existing.setPassword(user.getPassword());
            existing.setEmail(user.getEmail());
            existing.setEnabled(user.isEnabled());
            existing.setRole(user.getRole());

            return userRepository.save(existing);
        } catch (Exception e) {
            throw new RuntimeException("Error while updating user", e);
        }
    }

    // DELETE
    public void delete(Long id) {
        try {
            userRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Error while deleting user", e);
        }
    }
}
