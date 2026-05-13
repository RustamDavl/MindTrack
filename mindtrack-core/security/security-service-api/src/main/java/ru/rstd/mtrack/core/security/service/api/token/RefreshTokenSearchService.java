package ru.rstd.mtrack.core.security.service.api.token;

import ru.rstd.mtrack.core.security.model.token.RefreshToken;
import ru.rstd.mtrack.core.security.model.user.User;

import java.util.List;

public interface RefreshTokenSearchService {
    RefreshToken findByToken(String token);
    List<RefreshToken> findAllByUserAndRevokedFalse(User user);
}
