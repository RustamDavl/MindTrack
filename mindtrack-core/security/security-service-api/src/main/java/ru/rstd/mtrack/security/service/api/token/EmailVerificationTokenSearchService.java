package ru.rstd.mtrack.security.service.api.token;

import ru.rstd.mtrack.security.model.token.EmailVerificationToken;

public interface EmailVerificationTokenSearchService {
    EmailVerificationToken findByToken(String token);
}
