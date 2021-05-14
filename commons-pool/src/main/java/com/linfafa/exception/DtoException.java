package com.linfafa.exception;

/**
 * @author linmin
 * @since 1.0
 */
public class DtoException extends RuntimeException {

    public DtoException(String message, Exception cause) {
        super(message, cause);
    }

    public DtoException(String message) {
        super(message);
    }

    public DtoException(Exception cause) {
        super(cause);
    }

    public DtoException() {
    }

}
