package com.example.bankingsystemproject.business.impl;



import com.example.bankingsystemproject.business.SignUpUseCase;

import com.example.bankingsystemproject.config.exception.AlreadyExistsExceptionMessage;
import com.example.bankingsystemproject.configuration.exception.ExceptionMessage;
import com.example.bankingsystemproject.dto.SignUpRequestDTO;
import com.example.bankingsystemproject.dto.SignUpResponseDTO;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.bankingsystemproject.persistence.UserRepository;
import com.example.bankingsystemproject.persistence.entity.RoleEnum;
import com.example.bankingsystemproject.persistence.entity.User;
import com.example.bankingsystemproject.persistence.entity.UserRole;

import java.util.Set;

;

@Service
@RequiredArgsConstructor
public class SignUpUseCaseImpl implements SignUpUseCase {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public SignUpResponseDTO signUp(SignUpRequestDTO request) {
        if(userRepository.existsByEmail(request.getEmail())){
            throw new AlreadyExistsExceptionMessage();
        }
        else if(userRepository.existsByUsername(request.getUsername()))
        {
            throw new  AlreadyExistsExceptionMessage();
        }
        else if(userRepository.existsByFirstname(request.getFirstname()))
        {
            throw new  AlreadyExistsExceptionMessage();
        }
        else if(userRepository.existsByLastname(request.getLastname()))
        {
            throw new  AlreadyExistsExceptionMessage();
        }

        if(!request.getPassword().equals(request.getConfirmPassword())){
            throw new ExceptionMessage();
        }
        User savedUser = saveNewUser(request);

        return SignUpResponseDTO.builder()
                .userId(savedUser.getId())
                .build();


    }


    private User saveNewUser(SignUpRequestDTO request) {
        String encodedPassword = passwordEncoder.encode(request.getPassword());


        // countryEntity.
        User newUser = User.builder()

        .email(request.getEmail()).firstname(request.getFirstname()).lastname(request.getLastname())
                .username(request.getUsername()) .password(encodedPassword)
                .userImages(request.getUserImages())
                .build();
        newUser.setUserRoles(Set.of(UserRole.builder().user(newUser).name(RoleEnum.CUSTOMER).build()));
        return userRepository.save(newUser);
    }

}
