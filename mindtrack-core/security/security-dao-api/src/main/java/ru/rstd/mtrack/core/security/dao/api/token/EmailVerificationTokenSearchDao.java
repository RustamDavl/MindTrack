package ru.rstd.mtrack.core.security.dao.api.token;

import ru.rstd.mtrack.core.security.model.token.EmailVerificationToken;

import java.util.Optional;

public interface EmailVerificationTokenSearchDao {
    Optional<EmailVerificationToken> findByToken(String token);
}
