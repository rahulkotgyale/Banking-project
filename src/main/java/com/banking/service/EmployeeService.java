package com.banking.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banking.Repository.BranchRepository;
import com.banking.Repository.EmployeeRepository;
import com.banking.entity.Branch;
import com.banking.entity.Employee;

import jakarta.persistence.EntityNotFoundException;

@Service
public class EmployeeService {

	@Autowired
    private com.banking.Repository.EmployeeRepository employeeRepository;
	@Autowired
    private BranchRepository branchRepository;

    // Constructor Injection
    public EmployeeService(EmployeeRepository employeeRepository,
                           BranchRepository branchRepository) {
        this.employeeRepository = employeeRepository;
        this.branchRepository = branchRepository;
    }

    // ================= CREATE EMPLOYEE =================
    public Employee createEmployee(Long branchId, Employee employee) {

        Branch branch = branchRepository.findById(branchId)
                .orElseThrow(() ->
                        new EntityNotFoundException("Branch not found with id: " + branchId));

        employee.setBranch(branch);
        return employeeRepository.save(employee);
    }

    // ================= GET ALL EMPLOYEES =================
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    // ================= GET EMPLOYEE BY ID =================
    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException("Employee not found with id: " + id));
    }

    // ================= UPDATE EMPLOYEE =================
    public Employee updateEmployee(Long id, Employee employee) {

        Employee existing = getEmployeeById(id);

        existing.setName(employee.getName());
        existing.setRole(employee.getRole());
        existing.setSalary(employee.getSalary());

        return employeeRepository.save(existing);
    }

    // ================= DELETE EMPLOYEE =================
    public void deleteEmployee(Long id) {
        Employee employee = getEmployeeById(id);
        employeeRepository.delete(employee);
    }
}
