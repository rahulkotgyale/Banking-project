package com.banking.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.banking.entity.Branch;

@Repository
public interface BranchRepository extends JpaRepository<Branch, Long> {

}
