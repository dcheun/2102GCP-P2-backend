package dev.tdz.exceptions;

// To correspond with HTTP 404 status.
public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }
}
