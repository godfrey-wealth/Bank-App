package com.example.bankingsystemproject.business;

import com.example.bankingsystemproject.dto.BankingDTO;
import com.example.bankingsystemproject.dto.BankingRequest;
import com.example.bankingsystemproject.dto.BankingRequestDto;
import com.example.bankingsystemproject.persistence.entity.Banking;

import java.util.List;

public interface BankingUseCase {


    BankingRequestDto createBank(BankingRequestDto request);
    BankingDTO getBankById(Long Id);
    BankingDTO deposit(Long userId, double amount);
    BankingDTO withdraw(Long userId, double amount);
    List<BankingDTO> getAllBanks();
    void deleteAccount(Long Id);
    BankingDTO transferAmount(Long senderId, Long recipientId,  double amount);
}

