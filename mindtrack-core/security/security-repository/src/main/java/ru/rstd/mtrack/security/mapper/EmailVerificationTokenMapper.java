package ru.rstd.mtrack.security.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.rstd.mtrack.common.model.mapper.Mapper;
import ru.rstd.mtrack.security.entity.EmailVerificationTokenEntity;
import ru.rstd.mtrack.security.entity.UserEntity;
import ru.rstd.mtrack.security.model.token.EmailVerificationToken;

@Component
@RequiredArgsConstructor
public class EmailVerificationTokenMapper implements Mapper<EmailVerificationTokenEntity, EmailVerificationToken> {

    @Override
    public EmailVerificationTokenEntity toEntity(EmailVerificationToken model) {
        EmailVerificationTokenEntity entity = new EmailVerificationTokenEntity();
        entity.setToken(model.getToken());
        entity.setExpiresAt(model.getExpiresAt());
        UserEntity user = new UserEntity();
        user.setId(model.getUserId());
        entity.setUser(user);
        return entity;
    }

    @Override
    public EmailVerificationToken toModel(EmailVerificationTokenEntity entity) {
        EmailVerificationToken token = new EmailVerificationToken();
        token.setId(entity.getId());
        token.setToken(entity.getToken());
        token.setExpiresAt(entity.getExpiresAt());
        token.setUserId(entity.getUser().getId());
        return token;
    }
}
