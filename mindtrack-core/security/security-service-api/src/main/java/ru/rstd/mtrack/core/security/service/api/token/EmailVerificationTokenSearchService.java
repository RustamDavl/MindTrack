package ru.rstd.mtrack.core.security.service.api.token;

import ru.rstd.mtrack.core.security.model.token.EmailVerificationToken;

public interface EmailVerificationTokenSearchService {
    EmailVerificationToken findByToken(String token);
}
