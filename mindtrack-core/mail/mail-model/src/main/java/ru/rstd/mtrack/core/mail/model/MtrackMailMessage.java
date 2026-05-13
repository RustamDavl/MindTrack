package ru.rstd.mtrack.core.mail.model;

import java.util.Map;

public record MtrackMailMessage(
        EmailMessageType emailMessageType,
        String subject,
        String recipientEmail,
        Map<String, Object> properties
) {
}
