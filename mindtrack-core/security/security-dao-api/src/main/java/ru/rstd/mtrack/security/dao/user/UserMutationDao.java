package ru.rstd.mtrack.security.dao.user;


import ru.rstd.mtrack.security.model.user.User;

import java.util.UUID;

public interface UserMutationDao {
    User save(User user);
    void verifyEmail(UUID userId);
    User update(User user);
}
