package ru.rstd.mindly.mail.api;

import ru.rstd.mindly.mail.model.MindlyMailMessage;

public interface EmailService {
    void sendEmail(MindlyMailMessage mailMessage, boolean isMultipart);
}
