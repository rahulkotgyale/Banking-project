package com.banking.Repository;


import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.banking.entity.ATMAccount;

@Repository
public interface ATMAccountRepository extends JpaRepository<ATMAccount, Long> {
    Optional<ATMAccount> findByCardNumber(String cardNumber);
}
