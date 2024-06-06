package com.example.bankingsystemproject.configuration.exception;



public class InvalidPaymentException extends RuntimeException {
    public InvalidPaymentException(String message) {
        super(message);
    }
}
