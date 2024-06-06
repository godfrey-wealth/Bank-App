package com.example.bankingsystemproject.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@Builder
@AllArgsConstructor
public class AccountResponseDto {


    private Long accountId;

    private String firstname;

    private String message;

    private HttpStatus status;

    private String pinNumber;

    private String ibanCardno;
}
