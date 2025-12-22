package com.banking.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.banking.Repository.TypeRepository;
import com.banking.entity.Type;

@Service
public class TypeService {

	@Autowired
    private TypeRepository typeRepository;

    // CREATE
    public Type create(Type type) {
        try {
            return typeRepository.save(type);
        } catch (Exception e) {
            throw new RuntimeException("Error while creating type", e);
        }
    }

    // READ ALL
    public List<Type> getAll() {
        try {
            return typeRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Error while fetching types", e);
        }
    }

    // READ BY ID
    public Type getById(Long id) {
        try {
            return typeRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Type not found with id: " + id));
        } catch (Exception e) {
            throw new RuntimeException("Error while fetching type", e);
        }
    }

    // UPDATE
    public Type update(Long id, Type type) {
        try {
            Type existing = getById(id);

            existing.setTypeCategory(type.getTypeCategory());
            existing.setTypeValue(type.getTypeValue());
            existing.setActive(type.isActive());

            return typeRepository.save(existing);
        } catch (Exception e) {
            throw new RuntimeException("Error while updating type", e);
        }
    }

    // DELETE
    public void delete(Long id) {
        try {
            typeRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Error while deleting type", e);
        }
    }
}
