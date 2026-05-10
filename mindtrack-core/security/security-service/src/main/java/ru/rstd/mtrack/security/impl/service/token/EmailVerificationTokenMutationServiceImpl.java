package ru.rstd.mtrack.security.impl.service.token;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.keygen.BytesKeyGenerator;
import org.springframework.security.crypto.keygen.KeyGenerators;
import org.springframework.stereotype.Service;
import ru.rstd.mtrack.security.dao.token.EmailVerificationTokenMutationDao;
import ru.rstd.mtrack.security.dao.token.EmailVerificationTokenSearchDao;
import ru.rstd.mtrack.security.impl.properties.EmailConfirmationTokenExpirationConfig;
import ru.rstd.mtrack.security.model.token.EmailVerificationToken;
import ru.rstd.mtrack.security.model.token.MailConfirmationResponse;
import ru.rstd.mtrack.security.model.token.MailConfirmationStatus;
import ru.rstd.mtrack.security.model.user.User;
import ru.rstd.mtrack.security.service.api.token.EmailVerificationTokenMutationService;
import ru.rstd.mtrack.security.service.api.token.EmailVerificationTokenSearchService;
import ru.rstd.mtrack.security.service.api.user.UserMutationService;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.OffsetDateTime;
import java.util.Base64;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EmailVerificationTokenMutationServiceImpl implements EmailVerificationTokenMutationService {
    private static final BytesKeyGenerator TOKEN_GENERATOR = KeyGenerators.secureRandom(32);
    private final EmailVerificationTokenMutationDao emailVerificationTokenMutationDao;
    private final EmailVerificationTokenSearchService emailVerificationTokenSearchService;
    private final UserMutationService userMutationService;
    private final EmailConfirmationTokenExpirationConfig emailConfirmationTokenExpirationConfig;
    private final EmailVerificationTokenSearchDao  emailVerificationTokenSearchDao;

    @Override
    public String createAndSave(User user) {
        String token = Base64
                .getUrlEncoder()
                .withoutPadding()
                .encodeToString(TOKEN_GENERATOR.generateKey());
        EmailVerificationToken emailVerificationToken = new EmailVerificationToken();
        emailVerificationToken.setToken(sha256(token));
        emailVerificationToken.setExpiresAt(OffsetDateTime.now()
                .plus(emailConfirmationTokenExpirationConfig.getExpirationDuration())
        );
        emailVerificationToken.setUserId(user.getId());
        EmailVerificationToken savedToken = this.save(emailVerificationToken);
        return savedToken.getToken();
    }

    @Override
    public MailConfirmationResponse confirmEmailToken(String token) {
        Optional<EmailVerificationToken> emailVerificationToken = emailVerificationTokenSearchDao.findByToken(token);

        if(emailVerificationToken.isEmpty()) {
            return new MailConfirmationResponse(MailConfirmationStatus.INVALID, "Incorrect token");
        }

        EmailVerificationToken verificationToken = emailVerificationToken.get();

        if (verificationToken.getExpiresAt().isBefore(OffsetDateTime.now())) {
            return new MailConfirmationResponse(MailConfirmationStatus.EXPIRED, "Token expired");
        }

        UUID userId = verificationToken.getUserId();
        userMutationService.verifyEmail(userId);
        emailVerificationTokenMutationDao.delete(verificationToken.getId());

        return new MailConfirmationResponse(MailConfirmationStatus.SUCCESS, "Email verified.");
    }

    @Override
    public EmailVerificationToken save(EmailVerificationToken emailVerificationToken) {
        return emailVerificationTokenMutationDao.save(emailVerificationToken);
    }

    @Override
    public void delete(UUID id) {
        emailVerificationTokenMutationDao.delete(id);
    }

    private String sha256(String value) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(value.getBytes(StandardCharsets.UTF_8));
            return Base64.getUrlEncoder().withoutPadding().encodeToString(hash);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
