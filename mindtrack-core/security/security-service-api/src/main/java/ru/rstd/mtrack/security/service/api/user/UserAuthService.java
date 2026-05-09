package ru.rstd.mtrack.security.service.api.user;

import ru.rstd.mtrack.security.model.token.AccessWithRefreshToken;
import ru.rstd.mtrack.security.model.token.MailConfirmationResponse;
import ru.rstd.mtrack.security.model.user.UserRequest;

public interface UserAuthService {
    void register(UserRequest request);
    AccessWithRefreshToken login(UserRequest user);
    MailConfirmationResponse confirm(String token);
    AccessWithRefreshToken refreshToken(String token);
    void logout(String token);
}
