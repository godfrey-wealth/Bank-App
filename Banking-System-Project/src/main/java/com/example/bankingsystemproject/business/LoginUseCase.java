package com.example.bankingsystemproject.business;


import com.example.bankingsystemproject.dto.LoginRequestDTO;
import com.example.bankingsystemproject.dto.LoginResponseDTO;

public interface LoginUseCase {
    LoginResponseDTO login(LoginRequestDTO request);
}
