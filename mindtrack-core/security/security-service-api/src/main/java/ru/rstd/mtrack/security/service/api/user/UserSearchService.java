package ru.rstd.mtrack.security.service.api.user;

import ru.rstd.mtrack.security.model.user.User;

public interface UserSearchService {
    User findByEmail(String email);
}
