package com.banking.controller;

import java.util.List;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import com.banking.entity.Employee;
import com.banking.service.EmployeeService;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    // Constructor Injection (recommended)
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    // ================= CREATE EMPLOYEE =================
    // POST /employees/{branchId}
    @PostMapping("/{branchId}")
    public ResponseEntity<?> createEmployee(
            @PathVariable Long branchId,
            @RequestBody Employee employee) {

        if (branchId <= 0) {
            return new ResponseEntity<>("Invalid branch id", HttpStatus.BAD_REQUEST);
        }
        if (employee.getName() == null || employee.getName().isBlank()) {
            return new ResponseEntity<>("Employee name is required", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(
                employeeService.createEmployee(branchId, employee),
                HttpStatus.CREATED
        );
    }

    // ================= GET ALL EMPLOYEES =================
    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees() {
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }

    // ================= GET EMPLOYEE BY ID =================
    @GetMapping("/{id}")
    public ResponseEntity<?> getEmployeeById(@PathVariable Long id) {
        if (id <= 0) {
            return new ResponseEntity<>("Invalid employee id", HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(employeeService.getEmployeeById(id));
    }

    // ================= UPDATE EMPLOYEE =================
    @PutMapping("/{id}")
    public ResponseEntity<?> updateEmployee(
            @PathVariable Long id,
            @RequestBody Employee employee) {

        if (id <= 0) {
            return new ResponseEntity<>("Invalid employee id", HttpStatus.BAD_REQUEST);
        }
        if (employee.getName() != null && employee.getName().isBlank()) {
            return new ResponseEntity<>("Employee name cannot be empty", HttpStatus.BAD_REQUEST);
        }


        return ResponseEntity.ok(employeeService.updateEmployee(id, employee));
    }

    // ================= DELETE EMPLOYEE =================
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable Long id) {
        if (id <= 0) {
            return new ResponseEntity<>("Invalid employee id", HttpStatus.BAD_REQUEST);
        }
        employeeService.deleteEmployee(id);
        return ResponseEntity.noContent().build();
    }
}
