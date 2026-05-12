package ru.rstd.mtrack.core.security.service.api.token;

import ru.rstd.mtrack.core.security.model.token.RefreshToken;

public interface RefreshTokenMutationService {
    RefreshToken save(RefreshToken refreshToken);
}
