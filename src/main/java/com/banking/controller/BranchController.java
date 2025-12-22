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

    // CREATE BRANCH
    @PostMapping
    public ResponseEntity<Branch> createBranch(@RequestBody Branch branch) {
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

    // GET BRANCH BY ID
    @GetMapping("/{id}")
    public ResponseEntity<Branch> getBranchById(@PathVariable Long id) {
        return ResponseEntity.ok(branchService.getBranchById(id));
    }

    // UPDATE BRANCH
    @PutMapping("/{id}")
    public ResponseEntity<Branch> updateBranch(
            @PathVariable Long id,
            @RequestBody Branch branch) {

        return ResponseEntity.ok(branchService.updateBranch(id, branch));
    }

    // DELETE BRANCH
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBranch(@PathVariable Long id) {
        branchService.deleteBranch(id);
        return ResponseEntity.noContent().build();
    }
}
