package ru.rstd.mtrack.rest.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.rstd.mtrack.rest.api.UserAuthRestControllerApi;
import ru.rstd.mtrack.rest.dto.LoginRequest;
import ru.rstd.mtrack.rest.dto.MailConfirmRequest;
import ru.rstd.mtrack.rest.dto.MailConfirmResponse;
import ru.rstd.mtrack.rest.dto.RefreshTokenRequest;
import ru.rstd.mtrack.rest.dto.RegisterRequest;
import ru.rstd.mtrack.rest.dto.TokenResponse;
import ru.rstd.mtrack.rest.mapper.LoginRequestMapper;
import ru.rstd.mtrack.rest.mapper.RegisterRequestMapper;
import ru.rstd.mtrack.security.model.token.AccessWithRefreshToken;
import ru.rstd.mtrack.security.model.token.MailConfirmationResponse;
import ru.rstd.mtrack.security.model.user.UserRequest;
import ru.rstd.mtrack.security.service.api.user.UserAuthService;

@RestController
@RequiredArgsConstructor
public class UserAuthRestController implements UserAuthRestControllerApi {
    private final UserAuthService userAuthService;
    private final LoginRequestMapper loginRequestMapper;
    private final RegisterRequestMapper registerRequestMapper;

    @Override
    public ResponseEntity<String> registerUser(RegisterRequest registerRequest) {
        userAuthService.register(registerRequestMapper.toModel(registerRequest));
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body("Регистрация успешна. Проверьте почту для подтверждения email.");
    }

    @Override
    public ResponseEntity<Void> confirmEmail(String token) {
        userAuthService.confirm(token);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<MailConfirmResponse> confirmMail(MailConfirmRequest mailConfirmRequest) {
        MailConfirmationResponse response = userAuthService.confirm(mailConfirmRequest.getToken());
        MailConfirmResponse confirmResponse = new MailConfirmResponse();
        confirmResponse.setStatus(MailConfirmResponse.StatusEnum.valueOf(response.status().name()));
        confirmResponse.setMessage(response.message());
        return ResponseEntity.ok(confirmResponse);
    }

    @Override
    public ResponseEntity<TokenResponse> loginUser(LoginRequest loginRequest) {
        UserRequest user = loginRequestMapper.toModel(loginRequest);
        AccessWithRefreshToken tokens = userAuthService.login(user);
        TokenResponse tokenResponse = new TokenResponse();
        tokenResponse.accessToken(tokens.accessToken());
        tokenResponse.refreshToken(tokens.refreshToken());
        return ResponseEntity.ok(tokenResponse);
    }

    @Override
    public ResponseEntity<TokenResponse> refreshToken(RefreshTokenRequest refreshTokenRequest) {
        AccessWithRefreshToken tokens = userAuthService.refreshToken(refreshTokenRequest.getRefreshToken());
        TokenResponse tokenResponse = new TokenResponse();
        tokenResponse.accessToken(tokens.accessToken());
        tokenResponse.refreshToken(tokens.refreshToken());
        return ResponseEntity.ok(tokenResponse);
    }
}
