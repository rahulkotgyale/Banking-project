package com.banking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.banking.entity.Type;
import com.banking.service.TypeService;

@RestController
@RequestMapping("/types")
public class TypeController {

	@Autowired
    private TypeService typeService;

    // CREATE
    @PostMapping
    public ResponseEntity<?> create(@RequestBody Type request) {
        try {
            Type type = new Type();
            type.setTypeCategory(request.getTypeCategory());
            type.setTypeValue(request.getTypeValue());
            type.setActive(request.isActive());

            return new ResponseEntity<>(typeService.create(type), HttpStatus.CREATED);

        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // READ ALL
    @GetMapping
    public ResponseEntity<?> getAll() {
        try {
            List<Type> types = typeService.getAll();
            return new ResponseEntity<>(types, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // READ BY ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(typeService.getById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Type request) {
        try {
            Type type = new Type();
            type.setTypeCategory(request.getTypeCategory());
            type.setTypeValue(request.getTypeValue());
            type.setActive(request.isActive());

            return new ResponseEntity<>(typeService.update(id, type), HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            typeService.delete(id);
            return new ResponseEntity<>("Type deleted successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
