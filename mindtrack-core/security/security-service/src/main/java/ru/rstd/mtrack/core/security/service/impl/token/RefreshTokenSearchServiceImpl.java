package ru.rstd.mtrack.core.security.service.impl.token;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;
import ru.rstd.mtrack.core.security.dao.api.token.RefreshTokenSearchDao;
import ru.rstd.mtrack.core.security.model.token.RefreshToken;
import ru.rstd.mtrack.core.security.model.user.User;
import ru.rstd.mtrack.core.security.service.api.token.RefreshTokenSearchService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RefreshTokenSearchServiceImpl implements RefreshTokenSearchService {
    private final RefreshTokenSearchDao refreshTokenSearchDao;

    @Override
    public RefreshToken findByToken(String token) {
        return refreshTokenSearchDao.findByToken(token)
                .orElseThrow(() -> new BadCredentialsException("Invalid refresh token"));
    }

    @Override
    public List<RefreshToken> findAllByUserAndRevokedFalse(User user) {
        return List.of();
    }
}
