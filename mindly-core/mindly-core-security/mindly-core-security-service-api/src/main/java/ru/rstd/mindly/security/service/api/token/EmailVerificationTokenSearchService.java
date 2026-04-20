package ru.rstd.mindly.security.service.api.token;

import ru.rstd.mindly.security.model.token.EmailVerificationToken;

public interface EmailVerificationTokenSearchService {
    EmailVerificationToken findByToken(String token);
}
