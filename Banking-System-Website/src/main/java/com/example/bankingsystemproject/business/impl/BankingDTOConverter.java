package com.example.bankingsystemproject.business.impl;

import com.example.bankingsystemproject.dto.BankingDTO;
import com.example.bankingsystemproject.persistence.entity.Banking;

import java.util.Optional;

public final class BankingDTOConverter {


    private BankingDTOConverter ()
    {

    }
    public static BankingDTO convertBankingToDTO(Banking banking) {
        return BankingDTO.builder()
                .Id(banking.getId())
                .accountHolderName(banking.getAccountHolderName())
                .balance(banking.getBalance())
                .ibanCardNo(banking.getIbanCardNo())
                .pinNumber(banking.getPinNumber())
                .Date(banking.getDate())
                .account(AccountDTOConverter.convertAccountToDTO(banking.getAccount()))
                .user(UserDTOConverter.convertUserToDTO(banking.getUser()))
                .build();
    }
}
