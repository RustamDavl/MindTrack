package ru.rstd.mtrack.core.security.exception;

public class AccessDeniedException extends RuntimeException{
    public AccessDeniedException() {
    }
    public AccessDeniedException(String message) {
        super(message);
    }
}
