package com.example.bankingsystemproject.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;



@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequestDTO {
    @NotBlank(message = "Email cannot be empty")
    @Length(max = 50)
    private String email;

    @NotBlank
    @Length(min = 8, max = 50)
    private String password;

    private Long userId;
}
