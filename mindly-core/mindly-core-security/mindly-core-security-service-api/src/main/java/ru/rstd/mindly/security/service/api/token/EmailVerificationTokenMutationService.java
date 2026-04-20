package ru.rstd.mindly.security.service.api.token;

import ru.rstd.mindly.security.model.token.EmailVerificationToken;
import ru.rstd.mindly.security.model.token.MailConfirmationResponse;
import ru.rstd.mindly.security.model.user.User;

import java.util.UUID;

public interface EmailVerificationTokenMutationService {
    String createAndSave(User user);
    MailConfirmationResponse confirmEmailToken(String token);
    EmailVerificationToken save(EmailVerificationToken emailVerificationToken);
    void delete(UUID id);
}
