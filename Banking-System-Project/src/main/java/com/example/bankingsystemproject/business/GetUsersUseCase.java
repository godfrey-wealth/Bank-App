package com.example.bankingsystemproject.business;


import com.example.bankingsystemproject.dto.GetAllUsersRequestDTO;
import com.example.bankingsystemproject.dto.GetUsersResponseDTO;

public interface GetUsersUseCase {
    GetUsersResponseDTO getUsers(GetAllUsersRequestDTO requestDTO);
}
