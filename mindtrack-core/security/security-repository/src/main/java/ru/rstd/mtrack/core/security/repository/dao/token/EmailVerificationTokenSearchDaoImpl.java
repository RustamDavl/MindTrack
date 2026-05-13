package ru.rstd.mtrack.core.security.repository.dao.token;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.rstd.mtrack.core.security.dao.api.token.EmailVerificationTokenSearchDao;
import ru.rstd.mtrack.core.security.repository.jpa.EmailVerificationTokenJpaRepository;
import ru.rstd.mtrack.core.security.repository.mapper.EmailVerificationTokenMapper;
import ru.rstd.mtrack.core.security.model.token.EmailVerificationToken;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class EmailVerificationTokenSearchDaoImpl implements EmailVerificationTokenSearchDao {
    private final EmailVerificationTokenJpaRepository jpaRepository;
    private final EmailVerificationTokenMapper mapper;

    @Override
    public Optional<EmailVerificationToken> findByToken(String token) {
        return jpaRepository
                .findByToken(token)
                .map(mapper::toModel);
    }
}
