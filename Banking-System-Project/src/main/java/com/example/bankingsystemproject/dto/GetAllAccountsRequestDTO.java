package com.example.bankingsystemproject.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetAllAccountsRequestDTO {
   // private String description;

    private List<AccountDTO> accounts;
}
