package com.example.bankingsystemproject.config.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class InvalidAccessTokenException extends ResponseStatusException {
    public InvalidAccessTokenException(String ex){ super(HttpStatus.UNAUTHORIZED, ex);}
}
