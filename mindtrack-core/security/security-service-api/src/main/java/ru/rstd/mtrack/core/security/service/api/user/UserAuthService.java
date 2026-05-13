package ru.rstd.mtrack.core.security.service.api.user;

import ru.rstd.mtrack.core.security.model.token.AccessWithRefreshToken;
import ru.rstd.mtrack.core.security.model.token.MailConfirmationResponse;
import ru.rstd.mtrack.core.security.model.user.UserRequest;

public interface UserAuthService {
    void register(UserRequest request);
    AccessWithRefreshToken login(UserRequest user);
    MailConfirmationResponse confirm(String token);
    AccessWithRefreshToken refreshToken(String token);
    void logout(String token);
}
