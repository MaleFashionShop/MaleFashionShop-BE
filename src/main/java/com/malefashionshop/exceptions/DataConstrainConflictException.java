package com.malefashionshop.exceptions;

public class DataConstrainConflictException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public DataConstrainConflictException() {
        super();
    }

    public DataConstrainConflictException(String message) {
        super(message);
    }

    public DataConstrainConflictException(String message, Throwable cause) {
        super(message, cause);
    }
}
