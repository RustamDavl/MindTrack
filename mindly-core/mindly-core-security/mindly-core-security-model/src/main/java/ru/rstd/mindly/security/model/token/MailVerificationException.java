package ru.rstd.mindly.security.model.token;

public class MailVerificationException extends RuntimeException {
    public MailVerificationException() {
    }
    public MailVerificationException(String message) {
        super(message);
    }
}
