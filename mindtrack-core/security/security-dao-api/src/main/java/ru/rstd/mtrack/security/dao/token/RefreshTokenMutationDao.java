package ru.rstd.mtrack.security.dao.token;


import ru.rstd.mtrack.security.model.token.RefreshToken;

public interface RefreshTokenMutationDao {
    RefreshToken save(RefreshToken refreshToken);
}
