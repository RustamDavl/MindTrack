package ru.rstd.mtrack.core.security.impl.service.token;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.rstd.mtrack.core.security.dao.token.RefreshTokenMutationDao;
import ru.rstd.mtrack.core.security.model.token.RefreshToken;
import ru.rstd.mtrack.core.security.service.api.token.RefreshTokenMutationService;

@Service
@RequiredArgsConstructor
public class RefreshTokenMutationServiceImpl implements RefreshTokenMutationService {
    private final RefreshTokenMutationDao refreshTokenMutationDao;

    @Override
    public RefreshToken save(RefreshToken refreshToken) {
        return refreshTokenMutationDao.save(refreshToken);
    }
}
