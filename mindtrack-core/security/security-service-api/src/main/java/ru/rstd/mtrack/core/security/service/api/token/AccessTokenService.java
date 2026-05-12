package ru.rstd.mtrack.core.security.service.api.token;

import ru.rstd.mtrack.core.security.model.user.UserSecurityModel;

public interface AccessTokenService {
    String generate(UserSecurityModel user);
}
