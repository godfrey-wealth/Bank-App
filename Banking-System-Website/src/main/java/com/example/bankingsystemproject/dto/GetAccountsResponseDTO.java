package com.example.bankingsystemproject.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class GetAccountsResponseDTO {
    private List<AccountDTO> accounts;
}
