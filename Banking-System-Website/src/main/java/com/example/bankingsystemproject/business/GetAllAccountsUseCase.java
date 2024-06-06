package com.example.bankingsystemproject.business;

import com.example.bankingsystemproject.dto.AccessTokenDTO;
import com.example.bankingsystemproject.dto.AccountRequestDto;
import com.example.bankingsystemproject.dto.GetAccountsResponseDTO;
import com.example.bankingsystemproject.dto.GetAllAccountsRequestDTO;

public interface GetAllAccountsUseCase {

    GetAccountsResponseDTO getAccounts(GetAllAccountsRequestDTO allAccountsRequestDTO);


}