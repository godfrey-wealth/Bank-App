package com.example.bankingsystemproject.business;


import com.example.bankingsystemproject.dto.BankProductRequest;
import com.example.bankingsystemproject.persistence.entity.BankProducts;


import java.util.List;

public interface BankProductService {
    BankProducts addBankProduct(BankProductRequest bankProductRequest);
    BankProducts getBankProduct(Long id);
    List<BankProducts> getAllBankProducts();
}
