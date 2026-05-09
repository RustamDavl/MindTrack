package ru.rstd.mtrack.security.service.api.token;

import ru.rstd.mtrack.security.model.token.RefreshToken;

public interface RefreshTokenMutationService {
    RefreshToken save(RefreshToken refreshToken);
}
