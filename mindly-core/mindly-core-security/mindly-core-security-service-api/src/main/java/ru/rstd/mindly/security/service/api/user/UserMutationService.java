package ru.rstd.mindly.security.service.api.user;

import ru.rstd.mindly.security.model.user.User;

import java.util.UUID;

public interface UserMutationService {
    User save(User user);
    void verifyEmail(UUID userId);
    User update(User user);
}
