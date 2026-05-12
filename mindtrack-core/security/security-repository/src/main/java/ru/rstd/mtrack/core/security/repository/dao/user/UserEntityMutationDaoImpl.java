package ru.rstd.mtrack.core.security.repository.dao.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.rstd.mtrack.core.security.dao.api.user.UserMutationDao;
import ru.rstd.mtrack.core.security.repository.entity.UserEntity;
import ru.rstd.mtrack.core.security.repository.jpa.UserEntityJpaRepository;
import ru.rstd.mtrack.core.security.repository.mapper.UserMapper;
import ru.rstd.mtrack.core.security.model.user.User;

import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class UserEntityMutationDaoImpl implements UserMutationDao {
    private final UserMapper userMapper;
    private final UserEntityJpaRepository userEntityJpaRepository;

    @Override
    public User save(User user) {
        UserEntity userEntity = userMapper.toEntity(user);
        return userMapper.toModel(userEntityJpaRepository.save(userEntity));
    }

    @Override
    public void verifyEmail(UUID userId) {
        UserEntity user = userEntityJpaRepository.findById(userId).orElseThrow();
        user.setEmailVerified(true);
        userEntityJpaRepository.save(user);
    }

    @Override
    public User update(User user) {
        return null;
    }
}
