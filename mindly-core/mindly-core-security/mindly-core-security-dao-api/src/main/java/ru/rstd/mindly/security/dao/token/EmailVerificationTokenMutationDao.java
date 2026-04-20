package ru.rstd.mindly.security.dao.token;

import ru.rstd.mindly.security.model.token.EmailVerificationToken;

import java.util.UUID;

public interface EmailVerificationTokenMutationDao {
    EmailVerificationToken save(EmailVerificationToken emailVerificationToken);
    void delete(UUID id);
}
