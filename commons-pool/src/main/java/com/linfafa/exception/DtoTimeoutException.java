package com.linfafa.exception;

public class DtoTimeoutException extends DtoException{
    public DtoTimeoutException(String message, Exception cause) {
        super(message, cause);
    }

    public DtoTimeoutException(String message) {
        super(message);
    }

    public DtoTimeoutException(Exception cause) {
        super(cause);
    }

    public DtoTimeoutException() {
    }

}
