package com.banking.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import com.banking.Repository.NomineeRepository;
import com.banking.entity.Nominee;

@Service
public class NomineeService {

	@Autowired
    private NomineeRepository nomineeRepository;

    // CREATE
    public Nominee create(Nominee nominee) {
        try {
            return nomineeRepository.save(nominee);
        } catch (Exception e) {
            throw new RuntimeException("Error while creating nominee", e);
        }
    }

    // READ ALL
    public List<Nominee> getAll() {
        try {
            return nomineeRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Error while fetching nominees", e);
        }
    }

    // READ BY ID
    public Nominee getById(Long id) {
        try {
            return nomineeRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Nominee not found with id: " + id));
        } catch (Exception e) {
            throw new RuntimeException("Error while fetching nominee", e);
        }
    }

    // UPDATE
    public Nominee update(Long id, Nominee nominee) {
        try {
            Nominee existing = getById(id);

            existing.setNomineeName(nominee.getNomineeName());
            existing.setRelationship(nominee.getRelationship());
            existing.setAge(nominee.getAge());
            existing.setAccount(nominee.getAccount());

            return nomineeRepository.save(existing);
        } catch (Exception e) {
            throw new RuntimeException("Error while updating nominee", e);
        }
    }

    // DELETE
    public void delete(Long id) {
        try {
            nomineeRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Error while deleting nominee", e);
        }
    }
}
