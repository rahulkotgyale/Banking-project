package com.banking.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import com.banking.Repository.PermissionTypeRepository;
import com.banking.entity.PermissionType;

@Service
public class PermissionTypeService {

	@Autowired
    private PermissionTypeRepository permissionTypeRepository;

    // CREATE
    public PermissionType create(PermissionType permissionType) {
        try {
            return permissionTypeRepository.save(permissionType);
        } catch (Exception e) {
            throw new RuntimeException("Error while creating permission type", e);
        }
    }

    // READ ALL
    public List<PermissionType> getAll() {
        try {
            return permissionTypeRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Error while fetching permission types", e);
        }
    }

    // READ BY ID
    public PermissionType getById(Long id) {
        try {
            return permissionTypeRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("PermissionType not found with id: " + id));
        } catch (Exception e) {
            throw new RuntimeException("Error while fetching permission type", e);
        }
    }

    // UPDATE
    public PermissionType update(Long id, PermissionType permissionType) {
        try {
            PermissionType existing = getById(id);

            existing.setPermissionName(permissionType.getPermissionName());
            existing.setDescription(permissionType.getDescription());

            return permissionTypeRepository.save(existing);
        } catch (Exception e) {
            throw new RuntimeException("Error while updating permission type", e);
        }
    }

    // DELETE
    public void delete(Long id) {
        try {
            permissionTypeRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Error while deleting permission type", e);
        }
    }
}
