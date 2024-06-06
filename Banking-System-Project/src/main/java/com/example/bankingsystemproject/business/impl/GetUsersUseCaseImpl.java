package com.example.bankingsystemproject.business.impl;



import com.example.bankingsystemproject.business.GetUsersUseCase;
import com.example.bankingsystemproject.dto.GetAllUsersRequestDTO;
import com.example.bankingsystemproject.dto.GetUsersResponseDTO;
import com.example.bankingsystemproject.dto.UserDTO;
import com.example.bankingsystemproject.persistence.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetUsersUseCaseImpl implements GetUsersUseCase {
    private final UserRepository userRepository;

//    @Override
//    public GetUsersResponseDTO getUsers() {
//        return null;
//    }

    @Override
    public GetUsersResponseDTO getUsers(GetAllUsersRequestDTO requestDTO) {


        List<UserDTO> users = userRepository.findAll()
                .stream()
                .map(UserDTOConverter::convertUserToDTO)
                .toList();

        return GetUsersResponseDTO.builder().users(users).build();
    }
}
