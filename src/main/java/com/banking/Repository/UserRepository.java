package com.banking.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.banking.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

}
