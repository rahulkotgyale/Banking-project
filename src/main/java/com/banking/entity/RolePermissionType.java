package com.banking.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "role_permission_types")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RolePermissionType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rolePermissionId;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    @ManyToOne
    @JoinColumn(name = "permission_id")
    private PermissionType permissionType;
}
