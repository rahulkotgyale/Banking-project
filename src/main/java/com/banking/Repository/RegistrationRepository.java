package com.banking.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.banking.entity.Registration;

public interface RegistrationRepository extends JpaRepository<Registration, Long> {
	
	
    Optional<Registration> findByEmailAndPassword(String email, String password);

}
