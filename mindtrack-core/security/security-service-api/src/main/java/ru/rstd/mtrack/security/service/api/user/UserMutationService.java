package ru.rstd.mtrack.security.service.api.user;

import ru.rstd.mtrack.security.model.user.User;

import java.util.UUID;

public interface UserMutationService {
    User save(User user);
    void verifyEmail(UUID userId);
    User update(User user);
}
