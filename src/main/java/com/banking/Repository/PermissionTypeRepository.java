package com.banking.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

import com.banking.entity.PermissionType;

@Repository
public interface PermissionTypeRepository extends JpaRepository<PermissionType, Long>{

	PermissionType save(PermissionType permissionType);

	List<PermissionType> findAll();

    Optional<PermissionType> findById(Long id);

	void deleteById(Long id);

}
