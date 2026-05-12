package ru.rstd.mtrack.core.security.repository.dao.token;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.rstd.mtrack.core.security.dao.api.token.EmailVerificationTokenMutationDao;
import ru.rstd.mtrack.core.security.repository.entity.EmailVerificationTokenEntity;
import ru.rstd.mtrack.core.security.repository.jpa.EmailVerificationTokenJpaRepository;
import ru.rstd.mtrack.core.security.repository.mapper.EmailVerificationTokenMapper;
import ru.rstd.mtrack.core.security.model.token.EmailVerificationToken;

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
