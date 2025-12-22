package com.banking.Repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.banking.entity.PermissionType;
import com.banking.entity.Role;
import com.banking.entity.RolePermissionType;

public interface RolePermissionTypeRepository
        extends JpaRepository<RolePermissionType, Long> {

    // ✅ Valid derived query methods only
    List<RolePermissionType> findByRole(Role role);

    boolean existsByRoleAndPermissionType(Role role, 
                                          com.banking.entity.PermissionType permissionType);
}
