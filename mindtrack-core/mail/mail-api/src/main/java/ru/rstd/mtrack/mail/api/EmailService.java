package ru.rstd.mtrack.mail.api;

import ru.rstd.mtrack.mail.model.MtrackMailMessage;

public interface EmailService {
    void sendEmail(MtrackMailMessage mailMessage, boolean isMultipart);
}
