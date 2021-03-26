package dev.tdz.exceptions;

// To correspond with HTTP 400 status.
public class BadRequestException extends RuntimeException {
    public BadRequestException(String message) {
        super(message);
    }
}
