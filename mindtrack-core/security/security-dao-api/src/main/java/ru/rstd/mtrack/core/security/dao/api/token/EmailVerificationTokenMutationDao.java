package ru.rstd.mtrack.core.security.dao.api.token;

import ru.rstd.mtrack.core.security.model.token.EmailVerificationToken;

import java.util.UUID;

public interface EmailVerificationTokenMutationDao {
    EmailVerificationToken save(EmailVerificationToken emailVerificationToken);
    void delete(UUID id);
}
