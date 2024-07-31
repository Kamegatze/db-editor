package org.kamegatze.dbeditor.backend.info.metadata.repositories.exceptions;

public class NotFoundNameTableException extends RuntimeException {
    public NotFoundNameTableException() {
    }

    public NotFoundNameTableException(String message) {
        super(message);
    }

    public NotFoundNameTableException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotFoundNameTableException(Throwable cause) {
        super(cause);
    }

    public NotFoundNameTableException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
