package com.banking.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banking.Repository.BranchRepository;
import com.banking.entity.Branch;

import jakarta.persistence.EntityNotFoundException;

@Service
public class BranchService {

	@Autowired
    private com.banking.Repository.BranchRepository branchRepository;

    // Constructor Injection
    public BranchService(BranchRepository branchRepository) {
        this.branchRepository = branchRepository;
    }

    // CREATE BRANCH
    public Branch createBranch(Branch branch) {
        return branchRepository.save(branch);
    }

    // GET ALL BRANCHES
    public List<Branch> getAllBranches() {
        return branchRepository.findAll();
    }

    // GET BRANCH BY ID
    public Branch getBranchById(Long id) {
        return branchRepository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException("Branch not found with id: " + id));
    }

    // UPDATE BRANCH (ALL FIELDS)
    public Branch updateBranch(Long id, Branch branch) {

        Branch existing = getBranchById(id);

        existing.setBranchName(branch.getBranchName());
        existing.setCity(branch.getCity());
        existing.setIfscCode(branch.getIfscCode());

        return branchRepository.save(existing);
    }

    // DELETE BRANCH
    public void deleteBranch(Long id) {
        Branch branch = getBranchById(id);
        branchRepository.delete(branch);
    }
}
