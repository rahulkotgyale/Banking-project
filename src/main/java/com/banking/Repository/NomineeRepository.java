package com.banking.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.banking.entity.Nominee;

@Repository
public interface NomineeRepository extends JpaRepository<Nominee, Long> {

}
