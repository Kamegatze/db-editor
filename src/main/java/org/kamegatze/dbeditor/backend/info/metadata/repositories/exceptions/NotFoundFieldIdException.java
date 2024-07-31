package org.kamegatze.dbeditor.backend.info.metadata.repositories.exceptions;

public class NotFoundFieldIdException extends RuntimeException {
    public NotFoundFieldIdException() {
    }

    public NotFoundFieldIdException(String message) {
        super(message);
    }

    public NotFoundFieldIdException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotFoundFieldIdException(Throwable cause) {
        super(cause);
    }

    public NotFoundFieldIdException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
