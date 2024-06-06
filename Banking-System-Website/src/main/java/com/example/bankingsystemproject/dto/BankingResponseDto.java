package com.example.bankingsystemproject.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@Builder
@AllArgsConstructor
public class BankingResponseDto {

    private Long Id;
    private String accountHolderName;
    private double balance;
    private String ibanCardNo;

    private String message;

    private HttpStatus status;
}
