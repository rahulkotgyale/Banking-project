package com.banking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.banking.entity.Role;
import com.banking.Repository.RolePermissionTypeRepository;
import com.banking.entity.PermissionType;
import com.banking.entity.RolePermissionType;
import com.banking.service.RolePermissionTypeService;

@RestController
@RequestMapping("/role-permissions")
public class RolePermissionTypeCopntroller {

	@Autowired
    private RolePermissionTypeService service;

    // CREATE
    @PostMapping
    public ResponseEntity<?> create(@RequestBody RolePermissionType request) {
        try {
            RolePermissionType rpt = new RolePermissionType();

            Role role = new Role();
            role.setRoleId(request.getRole().getRoleId());
            rpt.setRole(role);

            PermissionType permission = new PermissionType();
            permission.setPermissionId(request.getPermissionType().getPermissionId());
            rpt.setPermissionType(permission);

            return new ResponseEntity<>(service.create(rpt), HttpStatus.CREATED);

        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // READ ALL
    @GetMapping
    public ResponseEntity<?> getAll() {
        try {
            List<RolePermissionType> list = service.getAll();
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // READ BY ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(service.getById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody RolePermissionType request) {
        try {
            RolePermissionType rpt = new RolePermissionType();

            Role role = new Role();
            role.setRoleId(request.getRole().getRoleId());
            rpt.setRole(role);

            PermissionType permission = new PermissionType();
            permission.setPermissionId(request.getPermissionType().getPermissionId());
            rpt.setPermissionType(permission);

            return new ResponseEntity<>(service.update(id, rpt), HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            service.delete(id);
            return new ResponseEntity<>("Role-Permission mapping deleted successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
