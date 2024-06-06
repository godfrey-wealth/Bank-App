package com.example.bankingsystemproject.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Lob;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.hibernate.validator.constraints.Length;


@Builder

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SignUpRequestDTO {
    @NotBlank(message = "Email cannot be empty")
    @Length(min = 6, max = 24)
   @Pattern(regexp = "^[a-z0-9](\\.?[a-z0-9]){5,}@gmail\\.com$")
    private String email;

    @NotBlank
    //@Length(max = 50)
    @Length(min = 6, max = 23)
//    @Pattern(regexp = "/^[A-z][A-z0-9-_]{5,23}$/")
    private String firstname;

    @NotBlank
    @Length(min = 6, max = 23)
//    @Pattern(regexp = "/^[A-z][A-z0-9-_]{5,23}$/")
    private String lastname;


    @NotBlank
    @Length(min =6 , max = 24)

//    @Pattern(regexp = "/^[A-z][A-z0-4-_]{4,24}$/")
    private String username;

    @NotBlank
    @Length(min = 8, max = 24)
//    @Pattern(regexp = "/^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9]).{8,24}$/")
    private String password;

    @NotBlank
    @Length(min = 8, max = 24)
    private String confirmPassword;

 @Lob
 @Column(length = 50000000)
 private byte[] userImages;

    private Long id;
}
