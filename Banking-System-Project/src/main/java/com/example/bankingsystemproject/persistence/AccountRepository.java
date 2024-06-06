package com.example.bankingsystemproject.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.bankingsystemproject.persistence.entity.Account;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    boolean existsByFirstName(String firstName);


    boolean existsById(Long userId);
    boolean existsByAddress(String address);

    boolean existsByIbanCardno(String ibanCardno);

    boolean existsByPinNumber(String ibanCardno);

    Optional<Account> findByIdAndUserId(Long id, Long userId);

    List<Account> findByUserId(Long userId);
}
