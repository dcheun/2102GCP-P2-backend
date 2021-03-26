package dev.tdz.exceptions;

// To correspond with HTTP 500 status.
public class InternalServerException extends RuntimeException {
    public InternalServerException(String message) {
        super(message);
    }
}
