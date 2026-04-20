package ru.rstd.mindly.security.impl.service.token;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.rstd.mindly.security.dao.token.RefreshTokenMutationDao;
import ru.rstd.mindly.security.model.token.RefreshToken;
import ru.rstd.mindly.security.service.api.token.RefreshTokenMutationService;

@Service
@RequiredArgsConstructor
public class RefreshTokenMutationServiceImpl implements RefreshTokenMutationService {
    private final RefreshTokenMutationDao refreshTokenMutationDao;

    @Override
    public RefreshToken save(RefreshToken refreshToken) {
        return refreshTokenMutationDao.save(refreshToken);
    }
}
