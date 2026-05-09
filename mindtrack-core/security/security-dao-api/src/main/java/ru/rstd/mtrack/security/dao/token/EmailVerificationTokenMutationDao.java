package ru.rstd.mtrack.security.dao.token;

import ru.rstd.mtrack.security.model.token.EmailVerificationToken;

import java.util.UUID;

public interface EmailVerificationTokenMutationDao {
    EmailVerificationToken save(EmailVerificationToken emailVerificationToken);
    void delete(UUID id);
}
