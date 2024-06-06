package com.example.bankingsystemproject.controller;


import com.example.bankingsystemproject.business.BankingUseCase;
import com.example.bankingsystemproject.dto.*;
import com.example.bankingsystemproject.persistence.entity.Banking;
import com.example.bankingsystemproject.security.auth.isauthenticated.isAuthenticated;
import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/banking")
@CrossOrigin(origins = "http://localhost:3000/")
@RequiredArgsConstructor
public class BankingController {

    private final BankingUseCase bankingUseCase;

    @isAuthenticated
    @RolesAllowed({"ROLE_CUSTOMER"})
    @PostMapping("/create")
    public ResponseEntity<?> createNewBank(@Valid @RequestBody BankingRequestDto accountRequestDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body("Invalid request payload");
        }

        BankingRequestDto response = bankingUseCase.createBank(accountRequestDto);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @isAuthenticated
    @RolesAllowed({"ROLE_CUSTOMER"})
    @GetMapping("/{id}")

    public ResponseEntity<BankingDTO> getBankById(@PathVariable Long id) {
        // Check if the user is authorized to access this bank information
        if (!isAuthorized(id)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        // Get banking information by ID
        BankingDTO bankingDTO = bankingUseCase.getBankById(id);

        if (bankingDTO != null) {
            return ResponseEntity.ok(bankingDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
//    public ResponseEntity<BankingDTO> getBankById(@PathVariable Long id) {
//        BankingDTO bankingDTO = bankingUseCase.getBankById(id);
//        if (bankingDTO != null) {
//            return ResponseEntity.ok(bankingDTO);
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }

    @isAuthenticated
    @RolesAllowed({"ROLE_CUSTOMER"})

    @PutMapping("/{bankId}/deposit")
    public ResponseEntity<BankingDTO> deposit(@PathVariable Long bankId, @RequestBody Map<String, Double> request) {
        // Check if the user is authorized to deposit into this bank account
        if (!isAuthorized(bankId)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        Double amount = request.get("amount");
        // Perform deposit
        BankingDTO bankingDTO = bankingUseCase.deposit(bankId,amount );

        if (bankingDTO != null) {
            return ResponseEntity.ok(bankingDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

//    public ResponseEntity<BankingDTO> deposit(@PathVariable Long bankId, @RequestBody Map<String, Double> request){ //@RequestParam double amount) {
//    Double amount = request.get("amount");
//    BankingDTO bankingDTO = bankingUseCase.deposit(bankId, amount);
//        if (bankingDTO != null) {
//            return ResponseEntity.ok(bankingDTO);
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }

    @isAuthenticated
    @RolesAllowed({"ROLE_CUSTOMER"})
    @PutMapping("/{bankId}/withdraw")
    public ResponseEntity<BankingDTO> withdraw(@PathVariable Long bankId,@RequestBody Map<String, Double> request){// @RequestParam double amount) {

        Double amount = request.get("amount");
        // Check if the user is authorized to withdraw from this bank account
        if (!isAuthorized(bankId)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        // Perform withdrawal
        BankingDTO bankingDTO = bankingUseCase.withdraw(bankId, amount);

        if (bankingDTO != null) {
            return ResponseEntity.ok(bankingDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
//    public ResponseEntity<BankingDTO> withdraw(@PathVariable Long bankId,@RequestBody Map<String, Double> request ){//@RequestParam double amount) {
//        Double amount = request.get("amount");
//
//        BankingDTO bankingDTO = bankingUseCase.withdraw(bankId, amount);
//        if (bankingDTO != null) {
//            return ResponseEntity.ok(bankingDTO);
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }

    @GetMapping("/all")
    public ResponseEntity<List<BankingDTO>> getAllBanks() {
        List<BankingDTO> bankingDTOs = bankingUseCase.getAllBanks();
        return ResponseEntity.ok(bankingDTOs);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAccount(@PathVariable Long id) {
        bankingUseCase.deleteAccount(id);
        return ResponseEntity.noContent().build();
    }

    @isAuthenticated
    @RolesAllowed({"ROLE_CUSTOMER"})
    @PutMapping("/{senderId}/transfer")
    public ResponseEntity<BankingDTO> transfer(@PathVariable Long senderId,
                                               @RequestParam Long recipientId,
//                                               @RequestBody BankingDTO banking
                                               @RequestBody Map<String, Double> request) {

       Double amount = request.get("amount");
        // Check if the user is authorized to transfer from sender's account
        if (!isAuthorized(senderId)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        // Perform transfer
        BankingDTO bankingDTO = bankingUseCase.transferAmount(senderId, recipientId, amount);

        if (bankingDTO != null) {
            return ResponseEntity.ok(bankingDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
//    public ResponseEntity<BankingDTO> transfer(@PathVariable Long senderId, @RequestParam Long recipientId, @RequestBody Map<String, Double> request){// @RequestParam double amount) {
//        Double amount = request.get("amount");
//
//        BankingDTO bankingDTO = bankingUseCase.transferAmount(senderId, recipientId, amount);
//        if (bankingDTO != null) {
//            return ResponseEntity.ok(bankingDTO);
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }

    // Helper method to check authorization
    private boolean isAuthorized(Long bankId) {
        // Implement your authorization logic here, e.g., check if the bank belongs to the logged-in user
        // For demonstration, let's assume that the user has access to all banks (replace it with your actual logic)
        return true;
    }

}

