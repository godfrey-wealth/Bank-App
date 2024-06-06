package com.example.bankingsystemproject.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.bankingsystemproject.persistence.entity.Banking;

@Repository
public interface BankRepository extends JpaRepository<Banking, Long> {
    boolean existsByIbanCardNo(String ibanCardno);
}
