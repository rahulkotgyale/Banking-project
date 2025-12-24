package com.banking.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.banking.entity.Registration;
import com.banking.service.RegistrationService;

@RestController
@RequestMapping("/registrations")
public class RegistrationController {

    @Autowired
    private RegistrationService registrationService;

    // ================= REGISTER =================
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Registration registration) {
        try {
            Registration saved = registrationService.registerUser(registration);

            Map<String, Object> response = new HashMap<>();
            response.put("id", saved.getId());
            response.put("name", saved.getName());
            response.put("email", saved.getEmail());
            response.put("mobile", saved.getMobile());

            return new ResponseEntity<>(response, HttpStatus.CREATED);

        } catch (Exception e) {
            return new ResponseEntity<>("Error while registering user", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // ================= LOGIN =================
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Registration loginRequest) {
        try {
            Registration user = registrationService
                    .login(loginRequest.getEmail(), loginRequest.getPassword());

            Map<String, Object> response = new HashMap<>();
            response.put("id", user.getId());
            response.put("name", user.getName());
            response.put("email", user.getEmail());
            response.put("mobile", user.getMobile());
            response.put("message", "Login successful");

            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>("Invalid email or password", HttpStatus.UNAUTHORIZED);
        }
    }

    // ================= GET ALL =================
    @GetMapping("/getAll")
    public ResponseEntity<?> getAllUsers() {
        try {
            List<Registration> users = registrationService.getAllUsers();
            return new ResponseEntity<>(users, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error while fetching users", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // ================= GET BY ID =================
    @GetMapping("/get/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id) {
        try {
            Registration user = registrationService.getUserById(id);

            Map<String, Object> response = new HashMap<>();
            response.put("id", user.getId());
            response.put("name", user.getName());
            response.put("email", user.getEmail());
            response.put("mobile", user.getMobile());

            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }
    }

    // ================= UPDATE =================
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateUser(
            @PathVariable Long id,
            @RequestBody Registration registration) {
        try {
            Registration updated = registrationService.updateUser(id, registration);

            Map<String, Object> response = new HashMap<>();
            response.put("id", updated.getId());
            response.put("name", updated.getName());
            response.put("email", updated.getEmail());
            response.put("mobile", updated.getMobile());

            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>("Error while updating user", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // ================= DELETE =================
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        try {
            Registration user = registrationService.getUserById(id);
            registrationService.deleteUser(id);

            Map<String, Object> response = new HashMap<>();
            response.put("id", user.getId());
            response.put("name", user.getName());
            response.put("email", user.getEmail());
            response.put("mobile", user.getMobile());

            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>("Error while deleting user", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
