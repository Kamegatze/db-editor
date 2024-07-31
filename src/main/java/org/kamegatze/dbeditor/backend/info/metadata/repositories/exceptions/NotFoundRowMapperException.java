package org.kamegatze.dbeditor.backend.info.metadata.repositories.exceptions;

public class NotFoundRowMapperException extends RuntimeException {
    public NotFoundRowMapperException() {
    }

    public NotFoundRowMapperException(String message) {
        super(message);
    }

    public NotFoundRowMapperException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotFoundRowMapperException(Throwable cause) {
        super(cause);
    }

    public NotFoundRowMapperException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
