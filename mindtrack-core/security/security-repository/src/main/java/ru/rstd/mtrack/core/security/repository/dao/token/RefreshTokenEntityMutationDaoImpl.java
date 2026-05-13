package ru.rstd.mtrack.core.security.repository.dao.token;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.rstd.mtrack.core.security.dao.api.token.RefreshTokenMutationDao;
import ru.rstd.mtrack.core.security.repository.entity.RefreshTokenEntity;
import ru.rstd.mtrack.core.security.repository.jpa.RefreshTokenJpaRepository;
import ru.rstd.mtrack.core.security.repository.mapper.RefreshTokenMapper;
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
