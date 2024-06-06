package com.example.bankingsystemproject.business;


import com.example.bankingsystemproject.dto.SignUpRequestDTO;
import com.example.bankingsystemproject.dto.SignUpResponseDTO;

public interface SignUpUseCase {
    SignUpResponseDTO signUp(SignUpRequestDTO request);
}
