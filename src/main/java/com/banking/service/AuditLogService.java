package com.banking.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import com.banking.Repository.AuditLogRepository;
import com.banking.entity.AuditLog;

@Service
public class AuditLogService {

	@Autowired
    private AuditLogRepository auditLogRepository;

    // CREATE
    public AuditLog create(AuditLog auditLog) {
        try {
            auditLog.setActionTime(LocalDateTime.now());
            return auditLogRepository.save(auditLog);
        } catch (Exception e) {
            throw new RuntimeException("Error while creating audit log", e);
        }
    }

    // READ ALL
    public List<AuditLog> getAll() {
        try {
            return auditLogRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Error while fetching audit logs", e);
        }
    }

    // READ BY ID
    public AuditLog getById(Long id) {
        try {
            return auditLogRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("AuditLog not found with id: " + id));
        } catch (Exception e) {
            throw new RuntimeException("Error while fetching audit log", e);
        }
    }

    // UPDATE
    public AuditLog update(Long id, AuditLog auditLog) {
        try {
            AuditLog existing = getById(id);

            existing.setAction(auditLog.getAction());
            existing.setPerformedBy(auditLog.getPerformedBy());
            existing.setRemarks(auditLog.getRemarks());

            return auditLogRepository.save(existing);
        } catch (Exception e) {
            throw new RuntimeException("Error while updating audit log", e);
        }
    }

    // DELETE
    public void delete(Long id) {
        try {
            auditLogRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Error while deleting audit log", e);
        }
    }
}
