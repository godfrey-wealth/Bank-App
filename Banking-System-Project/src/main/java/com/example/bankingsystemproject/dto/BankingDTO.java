package com.example.bankingsystemproject.dto;

import com.example.bankingsystemproject.persistence.entity.Banking;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BankingDTO {


     private Long Id;
    private String accountHolderName;
    private double amount;
    private double balance;
    private String ibanCardNo;
    private String pinNumber;

    private AccountDTO account;

    private UserDTO user;
    private String Date;

    public BankingDTO(Banking banking) {
        this.Id = banking.getId();
        this.accountHolderName = banking.getAccountHolderName();
        this.amount = banking.getAmount();
        this.balance = banking.getBalance();
        this.ibanCardNo = banking.getIbanCardNo();
        this.pinNumber = banking.getPinNumber();
        this.Date = banking.getDate();
        // Assuming AccountDTO has a constructor that accepts an Account object
        this.account = new AccountDTO(banking.getAccount());
        this.user = new UserDTO(banking.getUser());
    }
}
