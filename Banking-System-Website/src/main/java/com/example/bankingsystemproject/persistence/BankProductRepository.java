package com.example.bankingsystemproject.persistence;

import com.example.bankingsystemproject.persistence.entity.BankProducts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankProductRepository extends JpaRepository<BankProducts, Long> {

    boolean existsByName(String name);
}
