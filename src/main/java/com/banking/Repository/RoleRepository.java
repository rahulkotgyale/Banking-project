package com.banking.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.banking.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {

}
