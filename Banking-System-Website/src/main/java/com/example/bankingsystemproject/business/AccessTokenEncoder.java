package com.example.bankingsystemproject.business;


import com.example.bankingsystemproject.dto.AccessTokenDTO;

public interface AccessTokenEncoder {
    String encode(AccessTokenDTO accessToken);
}
