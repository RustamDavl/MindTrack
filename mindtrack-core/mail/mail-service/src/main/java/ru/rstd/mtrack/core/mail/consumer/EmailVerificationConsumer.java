package ru.rstd.mtrack.core.mail.consumer;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import ru.rstd.mtrack.core.mail.api.EmailService;
import ru.rstd.mtrack.core.mail.model.EmailMessageType;
import ru.rstd.mtrack.core.mail.model.MtrackMailMessage;
import ru.rstd.mtrack.core.mail.properties.AppMailProperties;
import ru.rstd.mtrack.core.security.model.user.UserWithEmailToken;

import java.util.Map;

@KafkaListener(
        topics = {"email_verification_message_event"},
        groupId = "email_verification_message_event_group"
)
@Component
@RequiredArgsConstructor
public class EmailVerificationConsumer {
    private final EmailService emailService;
    private final AppMailProperties properties;

    @KafkaHandler
    public void consumer(UserWithEmailToken userWithEmailToken) {
        MtrackMailMessage message = new MtrackMailMessage(
                EmailMessageType.EMAIL_CONFIRMATION,
                "Email confirmation",
                userWithEmailToken.user().getEmail(),
                Map.of("username", userWithEmailToken.user().getName(),
                        "confirmationUrl", properties.getMailConfirmationUrl().formatted(userWithEmailToken.token()))

        );

        emailService.sendEmail(message, false);
    }
}
