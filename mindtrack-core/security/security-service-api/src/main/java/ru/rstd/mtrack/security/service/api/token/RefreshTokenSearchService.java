package ru.rstd.mtrack.security.service.api.token;

import ru.rstd.mtrack.security.model.token.RefreshToken;
import ru.rstd.mtrack.security.model.user.User;

import java.util.List;

public interface RefreshTokenSearchService {
    RefreshToken findByToken(String token);
    List<RefreshToken> findAllByUserAndRevokedFalse(User user);
}
