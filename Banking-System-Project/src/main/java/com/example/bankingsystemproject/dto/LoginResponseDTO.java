package com.example.bankingsystemproject.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class LoginResponseDTO {
//    private UserDTO user;
    private List<String> roles;
    private String accessToken;

    //private Long userId;
    //private String email;


}
