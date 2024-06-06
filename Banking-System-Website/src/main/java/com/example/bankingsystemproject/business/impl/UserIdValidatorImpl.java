package com.example.bankingsystemproject.business.impl;



import com.example.bankingsystemproject.business.UserIdValidator;

import com.example.bankingsystemproject.configuration.exception.InvalidUserException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import com.example.bankingsystemproject.persistence.UserRepository;

@Component
@AllArgsConstructor
public class UserIdValidatorImpl implements UserIdValidator {
    private final UserRepository userRepository;

    @Override
    public void validateId(Long userId) {
        if (userId == null || !userRepository.existsById(userId)) {
            throw new InvalidUserException("ALL_EXISTS");
        }
    }
}
