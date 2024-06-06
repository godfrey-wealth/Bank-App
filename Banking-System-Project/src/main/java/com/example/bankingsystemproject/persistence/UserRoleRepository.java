package com.example.bankingsystemproject.persistence;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.bankingsystemproject.persistence.entity.RoleEnum;
import com.example.bankingsystemproject.persistence.entity.UserRole;

import java.util.Optional;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
    Optional<UserRole> findByName(RoleEnum name);
}
