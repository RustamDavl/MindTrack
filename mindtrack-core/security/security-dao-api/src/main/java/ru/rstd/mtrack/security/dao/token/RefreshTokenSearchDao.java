package ru.rstd.mtrack.security.dao.token;


import ru.rstd.mtrack.security.model.token.RefreshToken;
import ru.rstd.mtrack.security.model.user.User;

import java.util.List;
import java.util.Optional;

public interface RefreshTokenSearchDao {
    Optional<RefreshToken> findByToken(String token);
    List<RefreshToken> findAllByUserAndRevokedFalse(User user);
}
