package com.example.bankingsystemproject.controller;


import com.example.bankingsystemproject.business.AccountUseCase;
import com.example.bankingsystemproject.business.GetAllAccountsUseCase;
import com.example.bankingsystemproject.business.GetUserUseCase;
import com.example.bankingsystemproject.dto.*;
import com.example.bankingsystemproject.persistence.entity.RoleEnum;
import com.example.bankingsystemproject.security.auth.UnauthorizedDataAccessException;
import com.example.bankingsystemproject.security.auth.isauthenticated.isAuthenticated;
import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.BindingResult;

import java.util.Optional;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api/accounts")
@CrossOrigin(origins = "http://localhost:3000/")
//@Validated // Ensure that method arguments are validated
public class AccountController {


    private final AccountUseCase accountUseCase;
    private final GetUserUseCase getUserUseCase;
    private final AccessTokenDTO accessTokenDTO;

    private final GetAllAccountsUseCase getAllAccountsUseCase;

    @isAuthenticated
    @RolesAllowed({"ROLE_CUSTOMER"})
    @PostMapping("/add")

    public ResponseEntity<?> createAccount(@Valid @RequestBody AccountRequestDto accountRequestDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body("Invalid request payload");
        }

        AccountResponseDto response = accountUseCase.createAccount(accountRequestDto);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @isAuthenticated
    @RolesAllowed({"ROLE_CUSTOMER", "ROLE_CUSTOMER_SERVICE"})
    @GetMapping("/{Id}")
    public ResponseEntity<AccountDTO> getAccountDetails(@PathVariable Long Id) {
        final Optional<AccountDTO> accountOptional = accountUseCase.getAccountDetails(Id);
        final Optional<UserDTO> userOptional = getUserUseCase.getUser(accessTokenDTO.getUserId());
        if (userOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return accountOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }


    @isAuthenticated
    @RolesAllowed({"ROLE_CUSTOMER"})
    @GetMapping("/all")
    public GetAccountsResponseDTO getAllAccounts() {

        GetAllAccountsRequestDTO requestDTO = new GetAllAccountsRequestDTO();
        return getAllAccountsUseCase.getAccounts(requestDTO);
    }

//    @isAuthenticated
//    @RolesAllowed({ "ROLE_CUSTOMER"})
//    @GetMapping("/all")
//    public ResponseEntity<GetAccountsResponseDTO> getAllAccounts(AccessTokenDTO accessTokenDTO) {
//        // Check if the user is authenticated
//        if (!accessTokenDTO.hasRole(RoleEnum.CUSTOMER.name())) {
//            throw new UnauthorizedDataAccessException("User is not authenticated");
//        }
//
//        GetAccountsResponseDTO responseDTO = getAllAccountsUseCase.getAccounts(accessTokenDTO);
//        return ResponseEntity.ok(responseDTO);
//    }

}


