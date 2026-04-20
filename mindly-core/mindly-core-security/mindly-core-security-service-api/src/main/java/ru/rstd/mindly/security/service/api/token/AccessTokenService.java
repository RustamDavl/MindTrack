package ru.rstd.mindly.security.service.api.token;

import ru.rstd.mindly.security.model.user.UserSecurityModel;

public interface AccessTokenService {
    String generate(UserSecurityModel user);
}
