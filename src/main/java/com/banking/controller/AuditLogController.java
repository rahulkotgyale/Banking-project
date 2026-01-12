package com.banking.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;
import com.banking.entity.AuditLog;
import com.banking.service.AuditLogService;

@RestController
@RequestMapping("/audit-logs")
@RequiredArgsConstructor
public class AuditLogController {

    private final AuditLogService auditLogService;

    // CREATE with conditions
    @PostMapping
    public ResponseEntity<?> create(@RequestBody AuditLog auditLog) {
        try {
            if (auditLog.getAction() == null || auditLog.getAction().isBlank()) {
                return new ResponseEntity<>("Action is required", HttpStatus.BAD_REQUEST);
            }
            if (auditLog.getPerformedBy() == null || auditLog.getPerformedBy().isBlank()) {
                return new ResponseEntity<>("PerformedBy is required", HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(auditLogService.create(auditLog), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // READ ALL
    @GetMapping
    public ResponseEntity<?> getAll() {
        try {
            List<AuditLog> logs = auditLogService.getAll();
            return new ResponseEntity<>(logs, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // READ BY ID with condition
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        try {
            if (id <= 0) {
                return new ResponseEntity<>("Invalid audit log id", HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(auditLogService.getById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    // UPDATE with conditions
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody AuditLog auditLog) {
        try {
            if (id <= 0) {
                return new ResponseEntity<>("Invalid audit log id", HttpStatus.BAD_REQUEST);
            }
            if (auditLog.getAction() != null && auditLog.getAction().isBlank()) {
                return new ResponseEntity<>("Action cannot be empty", HttpStatus.BAD_REQUEST);
            }
            if (auditLog.getPerformedBy() != null && auditLog.getPerformedBy().isBlank()) {
                return new ResponseEntity<>("PerformedBy cannot be empty", HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(auditLogService.update(id, auditLog), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // DELETE with condition
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            if (id <= 0) {
                return new ResponseEntity<>("Invalid audit log id", HttpStatus.BAD_REQUEST);
            }
            auditLogService.delete(id);
            return new ResponseEntity<>("Audit log deleted successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
