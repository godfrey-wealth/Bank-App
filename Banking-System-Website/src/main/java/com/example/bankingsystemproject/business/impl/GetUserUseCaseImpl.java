package com.example.bankingsystemproject.business.impl;



import com.example.bankingsystemproject.business.GetUserUseCase;
import com.example.bankingsystemproject.dto.AccessTokenDTO;
import com.example.bankingsystemproject.dto.UserDTO;
import com.example.bankingsystemproject.persistence.UserRepository;
import com.example.bankingsystemproject.persistence.entity.RoleEnum;
import com.example.bankingsystemproject.persistence.entity.User;
import com.example.bankingsystemproject.security.auth.UnauthorizedDataAccessException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class GetUserUseCaseImpl implements GetUserUseCase {
    private final UserRepository userRepository;

    private final AccessTokenDTO requestAccessToken;

    @Override
    public Optional<UserDTO> getUser(Long Id) {

        Optional<User> userDTO = userRepository.findById(Id);


        {
            if (!requestAccessToken.hasRole(RoleEnum.ADMIN.name())) {
                if (requestAccessToken.getUserId() != Id) {
                    throw new UnauthorizedDataAccessException("STUDENT_ID_NOT_FROM_LOGGED_IN_USER");
                }

                // return userRepository.findById(Id).map(UserDTOConverter::convertUserToDTO);

            }
            return userRepository.findById(Id).map(UserDTOConverter::convertUserToDTO);
        }

    }

}



