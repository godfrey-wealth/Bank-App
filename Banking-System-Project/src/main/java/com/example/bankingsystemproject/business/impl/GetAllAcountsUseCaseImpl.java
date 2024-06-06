package com.example.bankingsystemproject.business.impl;

import com.example.bankingsystemproject.business.GetAllAccountsUseCase;
import com.example.bankingsystemproject.business.UserIdValidator;
import com.example.bankingsystemproject.configuration.exception.InvalidUserException;
import com.example.bankingsystemproject.dto.*;
import com.example.bankingsystemproject.persistence.AccountRepository;
import com.example.bankingsystemproject.persistence.UserRepository;
import com.example.bankingsystemproject.persistence.entity.Account;
import com.example.bankingsystemproject.persistence.entity.RoleEnum;
import com.example.bankingsystemproject.security.auth.UnauthorizedDataAccessException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class GetAllAcountsUseCaseImpl implements GetAllAccountsUseCase {

    private final AccountRepository accountRepository;
    private final UserRepository userRepository;
    private final AccessTokenDTO requestAccessToken;
    private final UserIdValidator userIdValidator;


    @Override
    public GetAccountsResponseDTO getAccounts(GetAllAccountsRequestDTO allAccountsRequestDTO) {
        List<Account> accounts = accountRepository.findAll();
        List<AccountDTO> accountDTOS = accounts.stream()
                .map(AccountDTOConverter::convertAccountToDTO)
                .collect(Collectors.toList());

        return GetAccountsResponseDTO.builder()
                .accounts(accountDTOS)
                .build();
    }


}
