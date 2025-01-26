package com.tech.teste.luiza.oracleebs.exception;

public class NotFoundExceptionHandler extends RuntimeException {
    public NotFoundExceptionHandler(String message) {
        super(message + " not found");
    }
}
