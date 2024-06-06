package com.example.bankingsystemproject.business.impl;


import com.example.bankingsystemproject.business.BankProductService;
import com.example.bankingsystemproject.config.exception.AlreadyExistsExceptionMessage;
import com.example.bankingsystemproject.dto.BankProductRequest;

import com.example.bankingsystemproject.persistence.BankProductRepository;
import com.example.bankingsystemproject.persistence.entity.BankProducts;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BankProductServiceImpl implements BankProductService {

    private final BankProductRepository bankProductRepository;


    @Override
    public BankProducts addBankProduct(BankProductRequest bankProductRequest) {
        BankProducts bankProduct = new BankProducts();
        // Map properties from request to entity
        // You can use ModelMapper or manual mapping here

        if(bankProductRepository.existsByName(bankProductRequest.getName())){

            throw  new AlreadyExistsExceptionMessage();
        }
        bankProduct.setName(bankProductRequest.getName());
        bankProduct.setDescription(bankProductRequest.getDescription());
        bankProduct.setType(bankProductRequest.getType());
        bankProduct.setProdImages(bankProductRequest.getProdImages());
        return bankProductRepository.save(bankProduct);
    }

    @Override
    public BankProducts getBankProduct(Long id) {
        return bankProductRepository.findById(id).orElse(null);
    }

    @Override
    public List<BankProducts> getAllBankProducts() {
        return bankProductRepository.findAll();
    }
}

