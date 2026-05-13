package ru.rstd.mtrack.core.security.service.api.user;

import ru.rstd.mtrack.core.security.model.user.User;

import java.util.UUID;

public interface UserMutationService {
    User save(User user);
    void verifyEmail(UUID userId);
    User update(User user);
}
