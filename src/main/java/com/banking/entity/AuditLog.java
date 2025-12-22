package com.banking.entity;

import java.time.LocalDateTime;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "audit_logs")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuditLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long auditId;

    private String action;           // CREATE_ACCOUNT, TRANSFER_MONEY
    private String performedBy;      // USERNAME
    private LocalDateTime actionTime;
    private String remarks;
}
