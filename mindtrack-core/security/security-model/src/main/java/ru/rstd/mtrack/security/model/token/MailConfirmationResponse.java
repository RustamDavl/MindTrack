package ru.rstd.mtrack.security.model.token;

public record MailConfirmationResponse(MailConfirmationStatus status, String message) {
}
