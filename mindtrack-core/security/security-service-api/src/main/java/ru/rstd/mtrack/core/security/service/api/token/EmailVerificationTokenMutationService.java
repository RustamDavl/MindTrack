package ru.rstd.mtrack.core.security.service.api.token;

import ru.rstd.mtrack.core.security.model.token.EmailVerificationToken;
import ru.rstd.mtrack.core.security.model.token.MailConfirmationResponse;
import ru.rstd.mtrack.core.security.model.user.User;

import java.util.UUID;

public interface EmailVerificationTokenMutationService {
    String createAndSave(User user);
    MailConfirmationResponse confirmEmailToken(String token);
    EmailVerificationToken save(EmailVerificationToken emailVerificationToken);
    void delete(UUID id);
}
