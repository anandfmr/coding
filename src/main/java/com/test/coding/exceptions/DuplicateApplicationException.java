package com.test.coding.exceptions;

public class DuplicateApplicationException extends Exception {
    private String message;

    public DuplicateApplicationException(String message) {
        super(message);
        message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
