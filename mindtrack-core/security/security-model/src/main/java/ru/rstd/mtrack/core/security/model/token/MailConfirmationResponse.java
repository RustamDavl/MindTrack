package ru.rstd.mtrack.core.security.model.token;

public record MailConfirmationResponse(MailConfirmationStatus status, String message) {
}
