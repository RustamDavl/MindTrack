package ru.rstd.mindly.security.dao.token;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.rstd.mindly.security.entity.EmailVerificationTokenEntity;
import ru.rstd.mindly.security.jpa.EmailVerificationTokenJpaRepository;
import ru.rstd.mindly.security.mapper.EmailVerificationTokenMapper;
import ru.rstd.mindly.security.model.token.EmailVerificationToken;

import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class EmailVerificationTokenMutationDaoImpl implements EmailVerificationTokenMutationDao {
    private final EmailVerificationTokenJpaRepository jpaRepository;
    private final EmailVerificationTokenMapper mapper;

    @Override
    public EmailVerificationToken save(EmailVerificationToken emailVerificationToken) {
        EmailVerificationTokenEntity entity = mapper.toEntity(emailVerificationToken);
        return mapper.toModel(jpaRepository.save(entity));
    }

    @Override
    public void delete(UUID id) {
        jpaRepository.deleteById(id);
    }
}
