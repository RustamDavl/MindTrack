package ru.rstd.mtrack.security.dao.token;

import ru.rstd.mtrack.security.model.token.EmailVerificationToken;

import java.util.Optional;

public interface EmailVerificationTokenSearchDao {
    Optional<EmailVerificationToken> findByToken(String token);
}
