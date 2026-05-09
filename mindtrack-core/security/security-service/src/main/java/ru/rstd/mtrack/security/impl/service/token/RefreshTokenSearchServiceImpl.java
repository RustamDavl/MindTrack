package ru.rstd.mtrack.security.impl.service.token;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;
import ru.rstd.mtrack.security.dao.token.RefreshTokenSearchDao;
import ru.rstd.mtrack.security.model.token.RefreshToken;
import ru.rstd.mtrack.security.model.user.User;
import ru.rstd.mtrack.security.service.api.token.RefreshTokenSearchService;

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
