package ru.rstd.mindly.security.dao.token;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.rstd.mindly.security.entity.RefreshTokenEntity;
import ru.rstd.mindly.security.jpa.RefreshTokenJpaRepository;
import ru.rstd.mindly.security.mapper.RefreshTokenMapper;
import ru.rstd.mindly.security.model.token.RefreshToken;

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
