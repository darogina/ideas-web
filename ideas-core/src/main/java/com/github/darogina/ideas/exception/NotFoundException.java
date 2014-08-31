package com.github.darogina.ideas.exception;

/**
 * Exception thrown when not result was found (for example findById with null return value)
 */
public class NotFoundException extends RuntimeException {

    private static final String DEFAULT_MESSAGE = "The requested resource could not be found";

    public NotFoundException() {
        super(DEFAULT_MESSAGE);
    }

    public NotFoundException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public NotFoundException(final String message) {
        super(message);
    }

    public NotFoundException(final Throwable cause) {
        super(cause);
    }

}
