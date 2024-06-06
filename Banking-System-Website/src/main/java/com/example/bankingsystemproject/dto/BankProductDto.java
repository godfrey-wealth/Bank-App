package com.example.bankingsystemproject.dto;


import com.example.bankingsystemproject.persistence.entity.AccountType;
import jakarta.persistence.Column;
import jakarta.persistence.Lob;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BankProductDto {

    private  Long id;

    private String name;
    private String description;

    private AccountType type;

    @Lob
    @Column(length = 50000000)
    private byte[] prodImages;
}


