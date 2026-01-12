package com.banking.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.banking.entity.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long>  {


}
