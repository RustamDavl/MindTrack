package ru.rstd.mindly.mail.impl;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import ru.rstd.mindly.mail.api.EmailService;
import ru.rstd.mindly.mail.model.EmailMessageType;
import ru.rstd.mindly.mail.model.MindlyMailMessage;

import java.nio.charset.StandardCharsets;


@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {
    private final JavaMailSender mailSender;
    private final TemplateEngine templateEngine;

    @Override
    public void sendEmail(MindlyMailMessage mindlyMailMessage, boolean isMultipart) {
        MimeMessage mailMessage = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mailMessage, isMultipart, StandardCharsets.UTF_8.name());
            helper.setSubject(mindlyMailMessage.subject());
            helper.setTo(mindlyMailMessage.recipientEmail());
            String emailContent = createContent(mindlyMailMessage);
            helper.setText(emailContent, true);

            mailSender.send(mailMessage);
        } catch (MessagingException e) {

        }
    }

    private String createContent(MindlyMailMessage mindlyMailMessage) {
        Context context = new Context();

        if(mindlyMailMessage.emailMessageType() == EmailMessageType.EMAIL_CONFIRMATION) {
            context.setVariable("username", mindlyMailMessage.properties().get("username"));
            context.setVariable("confirmationUrl", mindlyMailMessage.properties().get("confirmationUrl"));
        }

        return templateEngine.process("mail-confirmation", context);
    }
}
