package com.linfafa.exception;

/**
 * @author linmin
 * @since 1.0
 */
public class ConnectionException extends Exception{
    public ConnectionException() {
    }
    public ConnectionException(Exception cause) {
        super(cause);
    }

    public ConnectionException(String message, Exception cause) {
        super(message, cause);
    }

    public ConnectionException(String message) {
        super(message);
    }

}
