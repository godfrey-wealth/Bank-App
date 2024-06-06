package com.example.bankingsystemproject.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BankingRequestDto {


    private Long Id;
    private String accountHolderName;
    private double balance;
    private String ibanCardNo;
    private String pinNumber;

    private Long  accountId;
    private String Date;

    private Long userId;
    //private String message;

    private HttpStatus status;


}
