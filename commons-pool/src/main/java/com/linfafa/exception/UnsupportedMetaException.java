package com.linfafa.exception;

/**
 * @author linmin
 * @since 1.0
 */
public class UnsupportedMetaException extends DtoException{
    public UnsupportedMetaException(String message, Exception cause) {
        super(message, cause);
    }

    public UnsupportedMetaException(String message) {
        super(message);
    }

    public UnsupportedMetaException(Exception cause) {
        super(cause);
    }

    public UnsupportedMetaException() {
    }

}

