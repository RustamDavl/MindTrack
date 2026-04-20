package ru.rstd.mindly.security.dao.token;

import ru.rstd.mindly.security.model.token.EmailVerificationToken;

import java.util.Optional;

public interface EmailVerificationTokenSearchDao {
    Optional<EmailVerificationToken> findByToken(String token);
}
