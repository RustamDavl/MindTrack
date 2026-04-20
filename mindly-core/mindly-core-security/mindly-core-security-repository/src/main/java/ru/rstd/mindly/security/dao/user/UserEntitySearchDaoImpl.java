package ru.rstd.mindly.security.dao.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.rstd.mindly.security.jpa.UserEntityJpaRepository;
import ru.rstd.mindly.security.mapper.UserMapper;
import ru.rstd.mindly.security.model.user.User;

import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class UserEntitySearchDaoImpl implements UserSearchDao {
    private final UserMapper userMapper;
    private final UserEntityJpaRepository userEntityJpaRepository;

    @Override
    public Optional<User> findByEmail(String email) {
        return userEntityJpaRepository
                .findByEmail(email)
                .map(userMapper::toModel);
    }

    @Override
    public Optional<User> find(UUID id) {
        return userEntityJpaRepository
                .findById(id)
                .map(userMapper::toModel);
    }
}
