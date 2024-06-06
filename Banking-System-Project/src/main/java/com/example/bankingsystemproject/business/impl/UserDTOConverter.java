package com.example.bankingsystemproject.business.impl;



import com.example.bankingsystemproject.dto.UserDTO;
import com.example.bankingsystemproject.dto.UserRoleDTO;
import com.example.bankingsystemproject.persistence.entity.User;

import java.util.HashSet;
import java.util.Set;

public final class UserDTOConverter {
    private UserDTOConverter() {
    }

    public static UserDTO convertUserToDTO(User user) {
        Set<UserRoleDTO> roles = new HashSet<>();
        return UserDTO.builder().id(user.getId()).email(user.getEmail()).firstname(user.getFirstname()).lastname(user.getLastname())
                .username(user.getUsername())
                .userImages(user.getUserImages())
               // .role((UserRoleDTO) user.getUserRoles())
                .build();


    }

}