package ru.rstd.mtrack.security.model.token;

public class MailVerificationException extends RuntimeException {
    public MailVerificationException() {
    }
    public MailVerificationException(String message) {
        super(message);
    }
}
