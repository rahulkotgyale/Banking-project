package com.banking.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "permission_types")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PermissionType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long permissionId;

    @Column(unique = true)
    private String permissionName;   // READ, WRITE, DELETE, APPROVE

    private String description;
}
