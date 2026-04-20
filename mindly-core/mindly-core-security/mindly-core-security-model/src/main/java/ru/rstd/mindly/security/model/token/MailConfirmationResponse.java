package ru.rstd.mindly.security.model.token;

public record MailConfirmationResponse(MailConfirmationStatus status, String message) {
}
