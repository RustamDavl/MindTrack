package ru.rstd.mtrack.mail.model;

import java.util.Map;

public record MtrackMailMessage(
        EmailMessageType emailMessageType,
        String subject,
        String recipientEmail,
        Map<String, Object> properties
) {
}
