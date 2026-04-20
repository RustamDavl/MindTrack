package ru.rstd.mindly.security.service.api.token;

import ru.rstd.mindly.security.model.token.RefreshToken;

public interface RefreshTokenMutationService {
    RefreshToken save(RefreshToken refreshToken);
}
