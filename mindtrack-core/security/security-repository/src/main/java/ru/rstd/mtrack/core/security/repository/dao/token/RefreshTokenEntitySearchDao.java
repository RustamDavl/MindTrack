package ru.rstd.mtrack.core.security.repository.dao.token;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.rstd.mtrack.core.security.dao.api.token.RefreshTokenSearchDao;
import ru.rstd.mtrack.core.security.repository.jpa.RefreshTokenJpaRepository;
import ru.rstd.mtrack.core.security.repository.mapper.RefreshTokenMapper;
import ru.rstd.mtrack.core.security.model.token.RefreshToken;
import ru.rstd.mtrack.core.security.model.user.User;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class RefreshTokenEntitySearchDao implements RefreshTokenSearchDao {
    private final RefreshTokenMapper mapper;
    private final RefreshTokenJpaRepository refreshTokenJpaRepository;

    @Override
    public Optional<RefreshToken> findByToken(String token) {
        return refreshTokenJpaRepository
                .findByToken(token)
                .map(mapper::toModel);
    }

    @Override
    public List<RefreshToken> findAllByUserAndRevokedFalse(User user) {
        return List.of();
    }

}
