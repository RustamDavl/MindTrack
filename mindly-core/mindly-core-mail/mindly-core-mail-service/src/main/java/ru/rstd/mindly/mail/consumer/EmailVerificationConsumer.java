package ru.rstd.mindly.mail.consumer;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import ru.rstd.mindly.mail.api.EmailService;
import ru.rstd.mindly.mail.model.EmailMessageType;
import ru.rstd.mindly.mail.model.MindlyMailMessage;
import ru.rstd.mindly.mail.properties.AppMailProperties;
import ru.rstd.mindly.security.model.user.UserWithEmailToken;

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
        MindlyMailMessage message = new MindlyMailMessage(
                EmailMessageType.EMAIL_CONFIRMATION,
                "Email confirmation",
                userWithEmailToken.user().getEmail(),
                Map.of("username", userWithEmailToken.user().getName(),
                        "confirmationUrl", properties.getMailConfirmationUrl().formatted(userWithEmailToken.token()))

        );

        emailService.sendEmail(message, false);
    }
}
