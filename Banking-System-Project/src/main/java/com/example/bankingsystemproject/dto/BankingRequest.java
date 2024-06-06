package com.example.bankingsystemproject.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class BankingRequest {


    private String accountHolderName;
    private double balance;
    private String ibanCardNo;
    private String pinNumber;

    private Long accountId;
}
