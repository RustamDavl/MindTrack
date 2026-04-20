package ru.rstd.mindly.security.impl.service.token;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;
import ru.rstd.mindly.security.dao.token.RefreshTokenSearchDao;
import ru.rstd.mindly.security.model.token.RefreshToken;
import ru.rstd.mindly.security.model.user.User;
import ru.rstd.mindly.security.service.api.token.RefreshTokenSearchService;

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
