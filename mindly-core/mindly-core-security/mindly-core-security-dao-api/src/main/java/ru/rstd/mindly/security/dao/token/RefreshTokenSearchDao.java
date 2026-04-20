package ru.rstd.mindly.security.dao.token;


import ru.rstd.mindly.security.model.token.RefreshToken;
import ru.rstd.mindly.security.model.user.User;

import java.util.List;
import java.util.Optional;

public interface RefreshTokenSearchDao {
    Optional<RefreshToken> findByToken(String token);
    List<RefreshToken> findAllByUserAndRevokedFalse(User user);
}
