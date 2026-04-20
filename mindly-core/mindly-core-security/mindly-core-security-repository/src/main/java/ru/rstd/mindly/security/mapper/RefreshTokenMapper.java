package ru.rstd.mindly.security.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.rstd.mindly.common.model.mapper.Mapper;
import ru.rstd.mindly.security.entity.RefreshTokenEntity;
import ru.rstd.mindly.security.model.token.RefreshToken;


@Component
@RequiredArgsConstructor
public class RefreshTokenMapper implements Mapper<RefreshTokenEntity, RefreshToken> {
    private final UserMapper userMapper;

    @Override
    public RefreshTokenEntity toEntity(RefreshToken model) {
        RefreshTokenEntity entity = new RefreshTokenEntity();
        entity.setId(model.getId());
        entity.setCreatedAt(model.getCreatedAt());
        entity.setUser(userMapper.toEntity(model.getUser()));
        entity.setToken(model.getToken());
        entity.setExpiresAt(model.getExpiresAt());
        entity.setIsRevoked(model.getIsRevoked());
        return entity;
    }

    @Override
    public RefreshToken toModel(RefreshTokenEntity entity) {
        RefreshToken model = new RefreshToken();
        model.setId(entity.getId());
        model.setUser(userMapper.toModel(entity.getUser()));
        model.setToken(entity.getToken());
        model.setCreatedAt(entity.getCreatedAt());
        model.setExpiresAt(entity.getExpiresAt());
        model.setIsRevoked(entity.getIsRevoked());
        return model;
    }
}
