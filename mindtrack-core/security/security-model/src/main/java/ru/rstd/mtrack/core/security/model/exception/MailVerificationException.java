package ru.rstd.mtrack.core.security.model.exception;

public class MailVerificationException extends RuntimeException {
    public MailVerificationException() {
    }
    public MailVerificationException(String message) {
        super(message);
    }
}
