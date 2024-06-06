package com.example.bankingsystemproject.business.impl;


import com.example.bankingsystemproject.dto.UserRoleDTO;
import com.example.bankingsystemproject.persistence.entity.UserRole;

public final class UserRoleDTOConverter {
    UserRoleDTOConverter(){
    }

    public static UserRoleDTO convert(UserRole user){
                return  UserRoleDTO.builder()
                        .id(user.getId())
                        .name(user.getName())
                        //.user(user.getUser())
                .build();

    }
}
