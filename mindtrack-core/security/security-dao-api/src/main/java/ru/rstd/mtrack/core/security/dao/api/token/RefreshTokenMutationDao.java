package ru.rstd.mtrack.core.security.dao.api.token;


import ru.rstd.mtrack.core.security.model.token.RefreshToken;

public interface RefreshTokenMutationDao {
    RefreshToken save(RefreshToken refreshToken);
}
