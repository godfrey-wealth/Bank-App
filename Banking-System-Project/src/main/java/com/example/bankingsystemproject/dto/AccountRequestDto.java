package com.example.bankingsystemproject.dto;

import com.example.bankingsystemproject.persistence.entity.AccountType;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountRequestDto {

   //private  Long Id;
    private String firstName;
    private String email;
    private String address;
    private String gender;
    private String passportNo;
    @Column(name="type")
    @Enumerated(EnumType.STRING)
    private AccountType type;

    private String zipcode;

    //@NotBlank(message = "keep numbers and letters only")
    @Pattern(regexp = "^NL\\d{2}INGB\\d{10}$", message = "Invalid IBAN card number format. It should start with 'NL' followed by 2 digits, 'INGB', and 10 digits.")
    @Length(min = 14, max = 18)
    private String ibanCardno;



 private String pinNumber;



 @NotNull(message = "User ID cannot be null")
 private Long userId;

// @NotNull(message = "Banking ID cannot be null")
// private Long bankingId;
}
