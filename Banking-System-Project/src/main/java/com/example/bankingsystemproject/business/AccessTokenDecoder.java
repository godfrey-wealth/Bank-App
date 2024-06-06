package com.example.bankingsystemproject.business;


import com.example.bankingsystemproject.dto.AccessTokenDTO;

public interface AccessTokenDecoder {
    AccessTokenDTO decode(String accessTokenEncoded);
}
