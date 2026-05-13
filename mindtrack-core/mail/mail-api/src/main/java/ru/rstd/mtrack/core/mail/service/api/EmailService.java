package ru.rstd.mtrack.core.mail.service.api;

import ru.rstd.mtrack.core.mail.model.MtrackMailMessage;

public interface EmailService {
    void sendEmail(MtrackMailMessage mailMessage, boolean isMultipart);
}
