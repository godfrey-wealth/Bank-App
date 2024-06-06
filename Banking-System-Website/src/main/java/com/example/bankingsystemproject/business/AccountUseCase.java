package com.example.bankingsystemproject.business;


import com.example.bankingsystemproject.dto.AccountDTO;
import com.example.bankingsystemproject.dto.AccountRequestDto;
import com.example.bankingsystemproject.dto.AccountResponseDto;

import java.util.Optional;

public interface AccountUseCase {

  AccountResponseDto createAccount(AccountRequestDto accountRequestDto);

  public Optional<AccountDTO> getAccountDetails(Long Id);
}
