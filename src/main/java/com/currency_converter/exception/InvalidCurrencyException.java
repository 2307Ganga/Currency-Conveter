package com.currency_converter.exception;

public class InvalidCurrencyException extends RuntimeException {
    public InvalidCurrencyException(String message) {
        super(message);
    }
    public InvalidCurrencyException(String message, Throwable cause) {
        super(message, cause);
    }
    public InvalidCurrencyException() {
        super("Invalid currency data provided.");
    }
}
