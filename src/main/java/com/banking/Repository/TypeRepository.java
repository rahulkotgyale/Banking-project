package com.banking.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.banking.entity.Type;

@Repository
public interface TypeRepository extends JpaRepository<Type, Long> {

}
