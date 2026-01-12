package com.banking.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.banking.entity.Branch;
import com.banking.service.BranchService;

@RestController
@RequestMapping("/branches")
public class BranchController {

    private final BranchService branchService;

    // Constructor Injection
    public BranchController(BranchService branchService) {
        this.branchService = branchService;
    }

    // CREATE BRANCH with conditions
    @PostMapping
    public ResponseEntity<?> createBranch(@RequestBody Branch branch) {
        if (branch.getBranchName() == null || branch.getBranchName().isBlank()) {
            return new ResponseEntity<>("Branch name is required", HttpStatus.BAD_REQUEST);
        }
        if (branch.getCity() == null || branch.getCity().isBlank()) {
            return new ResponseEntity<>("City is required", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(
                branchService.createBranch(branch),
                HttpStatus.CREATED
        );
    }

    // GET ALL BRANCHES
    @GetMapping
    public ResponseEntity<List<Branch>> getAllBranches() {
        return ResponseEntity.ok(branchService.getAllBranches());
    }

    // GET BRANCH BY ID with condition
    @GetMapping("/{id}")
    public ResponseEntity<?> getBranchById(@PathVariable Long id) {
        if (id <= 0) {
            return new ResponseEntity<>("Invalid branch id", HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(branchService.getBranchById(id));
    }

    // UPDATE BRANCH with condition
    @PutMapping("/{id}")
    public ResponseEntity<?> updateBranch(
            @PathVariable Long id,
            @RequestBody Branch branch) {

        if (id <= 0) {
            return new ResponseEntity<>("Invalid branch id", HttpStatus.BAD_REQUEST);
        }
        if (branch.getBranchName() != null && branch.getBranchName().isBlank()) {
            return new ResponseEntity<>("Branch name cannot be empty", HttpStatus.BAD_REQUEST);
        }
        if (branch.getCity() != null && branch.getCity().isBlank()) {
            return new ResponseEntity<>("City cannot be empty", HttpStatus.BAD_REQUEST);
        }

        return ResponseEntity.ok(branchService.updateBranch(id, branch));
    }

    // DELETE BRANCH with condition
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBranch(@PathVariable Long id) {
        if (id <= 0) {
            return new ResponseEntity<>("Invalid branch id", HttpStatus.BAD_REQUEST);
        }
        branchService.deleteBranch(id);
        return ResponseEntity.noContent().build();
    }
}
