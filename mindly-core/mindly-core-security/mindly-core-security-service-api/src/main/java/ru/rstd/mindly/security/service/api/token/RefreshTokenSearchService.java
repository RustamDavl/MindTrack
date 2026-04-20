package ru.rstd.mindly.security.service.api.token;

import ru.rstd.mindly.security.model.token.RefreshToken;
import ru.rstd.mindly.security.model.user.User;

import java.util.List;

public interface RefreshTokenSearchService {
    RefreshToken findByToken(String token);
    List<RefreshToken> findAllByUserAndRevokedFalse(User user);
}
