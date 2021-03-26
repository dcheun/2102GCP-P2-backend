package dev.tdz.exceptions;

// Indicates issue with JWT, eg: no token specified or failed to verify signature.
// Will return a HTTP 401 Unauthorized status (Not Authenticated).
public class BadTokenException extends RuntimeException {
    public BadTokenException(String message) {
        super(message);
    }
}
