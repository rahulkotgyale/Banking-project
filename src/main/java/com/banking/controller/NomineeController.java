package com.banking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;
import com.banking.entity.Account;
import com.banking.entity.Nominee;
import com.banking.service.NomineeService;

@RestController
@RequestMapping("/nominees")
public class NomineeController {

	@Autowired
    private NomineeService nomineeService;

    // CREATE
    @PostMapping
    public ResponseEntity<?> create(@RequestBody Nominee request) {
        try {
            Nominee nominee = new Nominee();
            nominee.setNomineeName(request.getNomineeName());
            nominee.setRelationship(request.getRelationship());
            nominee.setAge(request.getAge());

            // Set account reference
            Account account = new Account();
            account.setAccountId(request.getAccount().getAccountId());
            nominee.setAccount(account);

            return new ResponseEntity<>(nomineeService.create(nominee), HttpStatus.CREATED);

        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // READ ALL
    @GetMapping
    public ResponseEntity<?> getAll() {
        try {
            List<Nominee> nominees = nomineeService.getAll();
            return new ResponseEntity<>(nominees, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // READ BY ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(nomineeService.getById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Nominee request) {
        try {
            Nominee nominee = new Nominee();
            nominee.setNomineeName(request.getNomineeName());
            nominee.setRelationship(request.getRelationship());
            nominee.setAge(request.getAge());

            Account account = new Account();
            account.setAccountId(request.getAccount().getAccountId());
            nominee.setAccount(account);

            return new ResponseEntity<>(nomineeService.update(id, nominee), HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            nomineeService.delete(id);
            return new ResponseEntity<>("Nominee deleted successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
