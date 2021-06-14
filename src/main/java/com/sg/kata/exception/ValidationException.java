package com.sg.kata.exception;

public class ValidationException extends RuntimeException {

    private static final long serialVersionUID = 6295447408291158081L;

    public ValidationException(String message) {
        super(message);
    }
}
