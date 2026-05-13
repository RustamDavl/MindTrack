package ru.rstd.mtrack.core.security.service.impl.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.rstd.mtrack.core.outbox.model.OutboxEventModel;
import ru.rstd.mtrack.core.outbox.model.OutboxEventStatus;
import ru.rstd.mtrack.core.outbox.model.OutboxEventType;
import ru.rstd.mtrack.core.outbox.service.mutation.OutboxEventMutationService;
import ru.rstd.mtrack.core.security.dao.api.user.UserSearchDao;
import ru.rstd.mtrack.core.security.model.token.AccessWithRefreshToken;
import ru.rstd.mtrack.core.security.model.token.MailConfirmationResponse;
import ru.rstd.mtrack.core.security.model.token.MailConfirmationStatus;
import ru.rstd.mtrack.core.security.exception.MailVerificationException;
import ru.rstd.mtrack.core.security.model.token.RefreshToken;
import ru.rstd.mtrack.core.security.model.user.Role;
import ru.rstd.mtrack.core.security.model.user.User;
import ru.rstd.mtrack.core.security.model.user.UserRole;
import ru.rstd.mtrack.core.security.model.user.UserSecurityModel;
import ru.rstd.mtrack.core.security.model.user.UserWithEmailToken;
import ru.rstd.mtrack.core.security.service.api.token.EmailVerificationTokenMutationService;
import ru.rstd.mtrack.core.security.service.api.token.RefreshTokenMutationService;
import ru.rstd.mtrack.core.security.service.api.token.RefreshTokenSearchService;
import ru.rstd.mtrack.core.security.service.api.token.AccessTokenService;
import ru.rstd.mtrack.core.security.service.api.user.UserAuthService;
import ru.rstd.mtrack.core.security.service.properties.JwtProperties;
import ru.rstd.mtrack.core.security.model.user.UserRequest;
import ru.rstd.mtrack.core.security.service.api.user.UserMutationService;
import ru.rstd.mtrack.core.security.service.api.user.UserSearchService;

import java.security.SecureRandom;
import java.time.OffsetDateTime;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserAuthServiceImpl implements UserAuthService {
    private static final SecureRandom SECURE_RANDOM = new SecureRandom();
    private final UserMutationService userMutationService;
    private final UserSearchService userSearchService;
    private final UserSearchDao userSearchDao;
    private final AuthenticationManager authenticationManager;
    private final RefreshTokenSearchService refreshTokenSearchService;
    private final PasswordEncoder passwordEncoder;
    private final AccessTokenService accessTokenService;
    private final JwtProperties jwtConfigProperties;
    private final RefreshTokenMutationService refreshTokenMutationService;
    private final OutboxEventMutationService outboxEventMutationService;
    private final EmailVerificationTokenMutationService emailVerificationTokenMutationService;

    @Override
    @Transactional
    public void register(UserRequest request) {
        String email = request.email();
        Optional<User> optionalUser = userSearchDao.findByEmail(email);

        if (optionalUser.isPresent()) {
            throw new IllegalStateException("User with such email already exists.");
        }

        User user = createUser(request);

        User savedUser = userMutationService.save(user);
        String token = emailVerificationTokenMutationService.createAndSave(savedUser);
        UserWithEmailToken userWithEmailToken = new UserWithEmailToken(savedUser, token);
        OutboxEventModel outboxEventModel = new OutboxEventModel();
        outboxEventModel.setEventType(OutboxEventType.USER_REGISTRATION);
        outboxEventModel.setStatus(OutboxEventStatus.NEW);
        outboxEventModel.setPayload(userWithEmailToken);
        outboxEventMutationService.save(outboxEventModel);
    }

    @Override
    @Transactional
    public AccessWithRefreshToken login(UserRequest user) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        user.email(),
                        user.rawPassword()
                )
        );

        UserSecurityModel userSecurityModel = (UserSecurityModel) authentication.getPrincipal();

        if(!userSecurityModel.getUser().getIsEmailVerified()) {
            throw new MailVerificationException("Email is not verified.");
        }

        User existerUser = userSearchService.findByEmail(userSecurityModel.getUser().getEmail());

        return issueTokensForUser(existerUser);
    }

    @Override
    public MailConfirmationResponse confirm(String token) {
        MailConfirmationResponse response = emailVerificationTokenMutationService.confirmEmailToken(token);

        if (response.status() == MailConfirmationStatus.EXPIRED) {
            throw new MailVerificationException("Email verification expired");
        }

        if (response.status() == MailConfirmationStatus.INVALID) {
            throw new IllegalStateException("Email verification token is invalid.");
        }

        return response;
    }

    @Override
    @Transactional
    public AccessWithRefreshToken refreshToken(String token) {
        RefreshToken refreshToken = refreshTokenSearchService.findByToken(token);

        if (refreshToken.getIsRevoked()) {
            throw new BadCredentialsException("Refresh token revoked");
        }

        if (refreshToken.getExpiresAt().isBefore(OffsetDateTime.now())) {
            throw new BadCredentialsException("Refresh token expired");
        }

        User user = refreshToken.getUser();

        refreshToken.setIsRevoked(true);
        refreshTokenMutationService.save(refreshToken);

        return issueTokensForUser(user);
    }

    @Override
    @Transactional
    public void logout(String refreshTokenValue) {
        RefreshToken refreshToken = refreshTokenSearchService.findByToken(refreshTokenValue);
        refreshToken.setIsRevoked(true);
        refreshTokenMutationService.save(refreshToken);
    }

    private AccessWithRefreshToken issueTokensForUser(User user) {
        UserSecurityModel securityUser = new UserSecurityModel(user);

        String accessToken = accessTokenService.generate(securityUser);

        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setToken(generateRefreshToken());
        refreshToken.setUser(user);
        refreshToken.setIsRevoked(false);
        refreshToken.setExpiresAt(OffsetDateTime.now().plus(jwtConfigProperties.getRefreshTokenTtl()));

        refreshTokenMutationService.save(refreshToken);

        return new AccessWithRefreshToken(accessToken, refreshToken.getToken());
    }

    private String generateRefreshToken() {
        byte[] bytes = new byte[64];
        SECURE_RANDOM.nextBytes(bytes);
        return Base64.getUrlEncoder().withoutPadding().encodeToString(bytes);
    }

    private User createUser(UserRequest userRequest) {
        User user = new User();
        user.setName(userRequest.name());
        user.setEmail(userRequest.email());
        user.setPasswordHash(passwordEncoder.encode(userRequest.rawPassword()));
        user.setRoles(List.of(new UserRole(Role.USER.name())));
        user.setIsEmailVerified(false);
        return user;
    }
}
