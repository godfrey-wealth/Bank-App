package com.example.bankingsystemproject.business.impl;

import com.example.bankingsystemproject.dto.AccountDTO;
import com.example.bankingsystemproject.persistence.entity.Account;

public  final class AccountDTOConverter {


    private AccountDTOConverter ()
    {

    }

    public  static AccountDTO convertAccountToDTO(Account account)
    {
        return  AccountDTO.builder()
                .id(account.getId())
                .firstName(account.getFirstName())
                .email(account.getEmail())
                .address(account.getAddress())
                .zipcode(account.getZipcode())
                .ibanCardno(account.getIbanCardno())
                .pinNumber(account.getPinNumber())
                .type(account.getType())
                .passportNo(account.getPassportNo())
                .gender(account.getGender())
                .user(UserDTOConverter.convertUserToDTO(account.getUser()))

                .build();
    }
}
