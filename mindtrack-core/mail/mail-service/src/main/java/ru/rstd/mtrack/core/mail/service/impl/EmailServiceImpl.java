package ru.rstd.mtrack.core.mail.service.impl;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import ru.rstd.mtrack.core.mail.service.api.EmailService;
import ru.rstd.mtrack.core.mail.model.EmailMessageType;
import ru.rstd.mtrack.core.mail.model.MtrackMailMessage;

import java.nio.charset.StandardCharsets;


@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {
    private final JavaMailSender mailSender;
    private final TemplateEngine templateEngine;

    @Override
    public void sendEmail(MtrackMailMessage mtrackMailMessage, boolean isMultipart) {
        MimeMessage mailMessage = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mailMessage, isMultipart, StandardCharsets.UTF_8.name());
            helper.setSubject(mtrackMailMessage.subject());
            helper.setTo(mtrackMailMessage.recipientEmail());
            String emailContent = createContent(mtrackMailMessage);
            helper.setText(emailContent, true);

            mailSender.send(mailMessage);
        } catch (MessagingException e) {

        }
    }

    private String createContent(MtrackMailMessage mtrackMailMessage) {
        Context context = new Context();

        if(mtrackMailMessage.emailMessageType() == EmailMessageType.EMAIL_CONFIRMATION) {
            context.setVariable("username", mtrackMailMessage.properties().get("username"));
            context.setVariable("confirmationUrl", mtrackMailMessage.properties().get("confirmationUrl"));
        }

        return templateEngine.process("mail-confirmation", context);
    }
}
