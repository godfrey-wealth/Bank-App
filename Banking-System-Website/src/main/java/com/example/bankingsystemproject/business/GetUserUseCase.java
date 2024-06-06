package com.example.bankingsystemproject.business;



import com.example.bankingsystemproject.config.exception.userNotFoundException;
import com.example.bankingsystemproject.dto.UserDTO;

import java.util.Optional;

public interface GetUserUseCase {
    Optional<UserDTO> getUser(Long Id) throws userNotFoundException;




}
