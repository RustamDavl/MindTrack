package ru.rstd.mindly.security.dao.user;


import ru.rstd.mindly.security.model.user.User;

import java.util.Optional;
import java.util.UUID;

public interface UserSearchDao {
    Optional<User> findByEmail(String email);
    Optional<User> find(UUID id);
}
