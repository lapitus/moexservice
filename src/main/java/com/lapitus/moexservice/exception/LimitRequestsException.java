package com.lapitus.moexservice.exception;

public class LimitRequestsException extends RuntimeException {
    public LimitRequestsException(String message) {
        super(message);
    }
}