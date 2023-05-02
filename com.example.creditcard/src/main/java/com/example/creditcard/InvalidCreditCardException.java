package com.example.creditcard;

@SuppressWarnings("serial")
public class InvalidCreditCardException extends Exception {
    public InvalidCreditCardException(String message) {
        super(message);
    }
}