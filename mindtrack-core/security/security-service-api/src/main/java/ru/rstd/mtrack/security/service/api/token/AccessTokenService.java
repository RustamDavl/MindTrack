package ru.rstd.mtrack.security.service.api.token;

import ru.rstd.mtrack.security.model.user.UserSecurityModel;

public interface AccessTokenService {
    String generate(UserSecurityModel user);
}
