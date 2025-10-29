package com.springRest.SpringRest.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springRest.SpringRest.Models.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    // You can define custom query methods here
    Account findByAccountNumber(String accountNumber);
}