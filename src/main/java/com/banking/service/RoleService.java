package com.banking.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import com.banking.Repository.RoleRepository;
import com.banking.entity.Role;

@Service
public class RoleService {

	@Autowired
    private RoleRepository roleRepository;

    // CREATE
    public Role create(Role role) {
        try {
            return roleRepository.save(role);
        } catch (Exception e) {
            throw new RuntimeException("Error while creating role", e);
        }
    }

    // READ ALL
    public List<Role> getAll() {
        try {
            return roleRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Error while fetching roles", e);
        }
    }

    // READ BY ID
    public Role getById(Long id) {
        try {
            return roleRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Role not found with id: " + id));
        } catch (Exception e) {
            throw new RuntimeException("Error while fetching role", e);
        }
    }

    // UPDATE
    public Role update(Long id, Role role) {
        try {
            Role existing = getById(id);
            existing.setRoleName(role.getRoleName());
            return roleRepository.save(existing);
        } catch (Exception e) {
            throw new RuntimeException("Error while updating role", e);
        }
    }

    // DELETE
    public void delete(Long id) {
        try {
            roleRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Error while deleting role", e);
        }
    }
}
