package com.ureii.ureiibackend.repository;

import com.ureii.ureiibackend.model.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BankAccountRepository extends JpaRepository<BankAccount, Long> {

    Optional<BankAccount> findByUserId(Long userId);

}
