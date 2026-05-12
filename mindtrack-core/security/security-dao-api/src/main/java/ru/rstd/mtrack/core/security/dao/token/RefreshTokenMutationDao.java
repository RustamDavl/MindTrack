package ru.rstd.mtrack.core.security.dao.token;


import ru.rstd.mtrack.core.security.model.token.RefreshToken;

public interface RefreshTokenMutationDao {
    RefreshToken save(RefreshToken refreshToken);
}
