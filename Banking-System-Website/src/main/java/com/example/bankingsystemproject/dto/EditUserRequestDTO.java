package com.example.bankingsystemproject.dto;


import com.example.bankingsystemproject.persistence.entity.UserRole;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EditUserRequestDTO {
    private Long id;

    @NotBlank(message = "Email cannot be empty")
//    @Length(max = 50)
    private String email;

    @NotBlank
//    @Length(max = 50)
    private String firstname;

    @NotBlank
//    @Length(max = 50)
    private String lastname;

    @NotBlank
//    @Length(max = 50)
    private String username;



    @NotBlank
//    @Length(min = 8, max = 50)
    private String password;


    @Enumerated(EnumType.STRING)
    @Column(name="role")
    private UserRole role;
}
