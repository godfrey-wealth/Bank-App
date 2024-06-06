package com.example.bankingsystemproject.dto;



import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.example.bankingsystemproject.persistence.entity.RoleEnum;
import com.example.bankingsystemproject.persistence.entity.User;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class UserRoleDTO {


    private Long id;

    @NotNull

    @Enumerated(EnumType.STRING)
    private RoleEnum name;


    private User user;
}

