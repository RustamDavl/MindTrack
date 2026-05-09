package ru.rstd.mtrack.security.impl.service.token;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.rstd.mtrack.security.dao.token.RefreshTokenMutationDao;
import ru.rstd.mtrack.security.model.token.RefreshToken;
import ru.rstd.mtrack.security.service.api.token.RefreshTokenMutationService;

@Service
@RequiredArgsConstructor
public class RefreshTokenMutationServiceImpl implements RefreshTokenMutationService {
    private final RefreshTokenMutationDao refreshTokenMutationDao;

    @Override
    public RefreshToken save(RefreshToken refreshToken) {
        return refreshTokenMutationDao.save(refreshToken);
    }
}
