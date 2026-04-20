package ru.rstd.mindly.mail.model;

import java.util.Map;

public record MindlyMailMessage(
        EmailMessageType emailMessageType,
        String subject,
        String recipientEmail,
        Map<String, Object> properties
) {
}
