package com.example.bankingsystemproject.business;


import com.example.bankingsystemproject.configuration.exception.InvalidUserException;

public interface UserIdValidator {
    void validateId(Long countryId) throws InvalidUserException;
}
