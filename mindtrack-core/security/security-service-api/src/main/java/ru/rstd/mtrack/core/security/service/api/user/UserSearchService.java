package ru.rstd.mtrack.core.security.service.api.user;

import ru.rstd.mtrack.core.security.model.user.User;

public interface UserSearchService {
    User findByEmail(String email);
}
