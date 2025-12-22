package com.banking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.banking.entity.PermissionType;
import com.banking.service.PermissionTypeService;

@RestController
@RequestMapping("/permission-types")
public class PermissionTypeController {

	@Autowired
    private PermissionTypeService permissionTypeService;

    // CREATE
    @PostMapping
    public ResponseEntity<?> create(@RequestBody PermissionType request) {
        try {
            PermissionType permissionType = new PermissionType();
            permissionType.setPermissionName(request.getPermissionName());
            permissionType.setDescription(request.getDescription());

            return new ResponseEntity<>(permissionTypeService.create(permissionType), HttpStatus.CREATED);

        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // READ ALL
    @GetMapping
    public ResponseEntity<?> getAll() {
        try {
            List<PermissionType> permissionTypes = permissionTypeService.getAll();
            return new ResponseEntity<>(permissionTypes, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // READ BY ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(permissionTypeService.getById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody PermissionType request) {
        try {
            PermissionType permissionType = new PermissionType();
            permissionType.setPermissionName(request.getPermissionName());
            permissionType.setDescription(request.getDescription());

            return new ResponseEntity<>(permissionTypeService.update(id, permissionType), HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            permissionTypeService.delete(id);
            return new ResponseEntity<>("Permission type deleted successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
