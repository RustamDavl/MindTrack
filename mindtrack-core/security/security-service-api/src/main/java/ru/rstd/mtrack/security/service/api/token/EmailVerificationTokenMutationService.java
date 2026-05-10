package ru.rstd.mtrack.security.service.api.token;

import ru.rstd.mtrack.security.model.token.EmailVerificationToken;
import ru.rstd.mtrack.security.model.token.MailConfirmationResponse;
import ru.rstd.mtrack.security.model.user.User;

import java.util.UUID;

public interface EmailVerificationTokenMutationService {
    String createAndSave(User user);
    MailConfirmationResponse confirmEmailToken(String token);
    EmailVerificationToken save(EmailVerificationToken emailVerificationToken);
    void delete(UUID id);
}
