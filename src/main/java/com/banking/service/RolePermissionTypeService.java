package com.banking.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banking.Repository.RolePermissionTypeRepository;
import com.banking.entity.RolePermissionType;

@Service
public class RolePermissionTypeService {

	@Autowired
    private RolePermissionTypeRepository repository;

    // CREATE
    public RolePermissionType create(RolePermissionType rpt) {
        try {
            return repository.save(rpt);
        } catch (Exception e) {
            throw new RuntimeException("Error while creating role-permission mapping", e);
        }
    }

    // READ ALL
    public List<RolePermissionType> getAll() {
        try {
            return repository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Error while fetching role-permission mappings", e);
        }
    }

    // READ BY ID
    public RolePermissionType getById(Long id) {
        try {
            return repository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Mapping not found with id: " + id));
        } catch (Exception e) {
            throw new RuntimeException("Error while fetching role-permission mapping", e);
        }
    }

    // UPDATE
    public RolePermissionType update(Long id, RolePermissionType rpt) {
        try {
            RolePermissionType existing = repository.findById(id)
                    .orElseThrow(() -> new RuntimeException("RolePermissionType not found"));

            existing.setRole(rpt.getRole());                       // ✅ entity setter
            existing.setPermissionType(rpt.getPermissionType());   // ✅ entity setter

            return repository.save(existing);                      // ✅ save entity
        } catch (Exception e) {
            throw new RuntimeException("Error while updating role-permission mapping", e);
        }
    }

    // DELETE
    public void delete(Long id) {
        try {
            repository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Error while deleting role-permission mapping", e);
        }
    }
}
