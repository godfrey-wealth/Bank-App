package com.example.bankingsystemproject.security.auth;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class UnauthorizedDataAccessException extends ResponseStatusException {
    public UnauthorizedDataAccessException(String errcode){super(HttpStatus.FORBIDDEN, errcode);}
}
