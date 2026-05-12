package ru.rstd.mtrack.core.security.dao.token;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.rstd.mtrack.core.security.entity.RefreshTokenEntity;
import ru.rstd.mtrack.core.security.jpa.RefreshTokenJpaRepository;
import ru.rstd.mtrack.core.security.mapper.RefreshTokenMapper;
import ru.rstd.mtrack.core.security.model.token.RefreshToken;

@Repository
@RequiredArgsConstructor
public class RefreshTokenEntityMutationDaoImpl implements RefreshTokenMutationDao {
    private final RefreshTokenMapper mapper;
    private final RefreshTokenJpaRepository jpaRepository;

    @Override
    public RefreshToken save(RefreshToken refreshToken) {
        RefreshTokenEntity entity = mapper.toEntity(refreshToken);
        return mapper.toModel(jpaRepository.save(entity));
    }
}
