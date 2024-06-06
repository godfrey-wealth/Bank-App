package com.example.bankingsystemproject.business.impl;



import com.example.bankingsystemproject.business.AccessTokenEncoder;
import com.example.bankingsystemproject.business.LoginUseCase;

import com.example.bankingsystemproject.config.exception.InvalidCredentialsException;
import com.example.bankingsystemproject.dto.AccessTokenDTO;
import com.example.bankingsystemproject.dto.LoginRequestDTO;
import com.example.bankingsystemproject.dto.LoginResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.bankingsystemproject.persistence.UserRepository;
import com.example.bankingsystemproject.persistence.UserRoleRepository;
import com.example.bankingsystemproject.persistence.entity.User;

import java.util.List;



@RequiredArgsConstructor

@Service
public class LoginUseCaseImpl implements LoginUseCase {


    private final UserRepository userRepository;
    private final AccessTokenEncoder accessTokenEncoder;
    private final PasswordEncoder passwordEncoder;

    private final UserRoleRepository userRoleRepository;



    @Override
    public LoginResponseDTO login(LoginRequestDTO request) {


        User user = userRepository.findByEmail(request.getEmail());


        if (user == null) {
            throw new InvalidCredentialsException();
        }


        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new InvalidCredentialsException();
        }



          List<String>  roles = user.getUserRoles().stream().map(userRole -> userRole.getName().toString()).toList();
            Long userId = user != null ? user.getId() : null;
            roles = user.getUserRoles().stream().map(userRole -> userRole.getName().toString()).toList();

            String accessToken = accessTokenEncoder.encode(AccessTokenDTO.builder().subject(user.getEmail()).userId(userId)
                    .roles(roles).build());

            return LoginResponseDTO.builder().roles(roles).accessToken(accessToken).build();
        }


    }


















