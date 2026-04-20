package ru.rstd.mindly.security.dao.token;


import ru.rstd.mindly.security.model.token.RefreshToken;

public interface RefreshTokenMutationDao {
    RefreshToken save(RefreshToken refreshToken);
}
