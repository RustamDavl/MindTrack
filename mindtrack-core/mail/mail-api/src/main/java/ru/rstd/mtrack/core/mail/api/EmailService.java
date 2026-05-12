package ru.rstd.mtrack.core.mail.api;

import ru.rstd.mtrack.core.mail.model.MtrackMailMessage;

public interface EmailService {
    void sendEmail(MtrackMailMessage mailMessage, boolean isMultipart);
}
