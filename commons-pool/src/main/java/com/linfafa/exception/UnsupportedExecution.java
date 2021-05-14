package com.linfafa.exception;

public class UnsupportedExecution extends DtoException{
    public UnsupportedExecution(String message, Exception cause) {
        super(message, cause);
    }

    public UnsupportedExecution(String message) {
        super(message);
    }

    public UnsupportedExecution(Exception cause) {
        super(cause);
    }

    public UnsupportedExecution() {
    }

}
