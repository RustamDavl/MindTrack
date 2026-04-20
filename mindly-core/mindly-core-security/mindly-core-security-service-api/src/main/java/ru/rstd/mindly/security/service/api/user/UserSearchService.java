package ru.rstd.mindly.security.service.api.user;

import ru.rstd.mindly.security.model.user.User;

public interface UserSearchService {
    User findByEmail(String email);
}
