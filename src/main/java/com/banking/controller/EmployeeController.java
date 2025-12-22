package com.banking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.banking.entity.Employee;
import com.banking.service.EmployeeService;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

	@Autowired
    private EmployeeService employeeService;

    // Constructor Injection
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    // ================= CREATE EMPLOYEE =================
    // POST /employees/{branchId}
    @PostMapping("/{branchId}")
    public ResponseEntity<Employee> createEmployee(
            @PathVariable Long branchId,
            @RequestBody Employee employee) {

        return new ResponseEntity<>(
                employeeService.createEmployee(branchId, employee),
                HttpStatus.CREATED
        );
    }

    // ================= GET ALL EMPLOYEES =================
    // GET /employees
    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees() {
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }

    // ================= GET EMPLOYEE BY ID =================
    // GET /employees/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
        return ResponseEntity.ok(employeeService.getEmployeeById(id));
    }

    // ================= UPDATE EMPLOYEE =================
    // PUT /employees/{id}
    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(
            @PathVariable Long id,
            @RequestBody Employee employee) {

        return ResponseEntity.ok(employeeService.updateEmployee(id, employee));
    }

    // ================= DELETE EMPLOYEE =================
    // DELETE /employees/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.noContent().build();
    }
}
