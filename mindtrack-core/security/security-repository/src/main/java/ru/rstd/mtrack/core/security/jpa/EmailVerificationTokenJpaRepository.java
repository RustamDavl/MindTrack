package ru.rstd.mtrack.core.security.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.rstd.mtrack.core.security.entity.EmailVerificationTokenEntity;

import java.util.Optional;
import java.util.UUID;

public interface EmailVerificationTokenJpaRepository extends JpaRepository<EmailVerificationTokenEntity, UUID> {
    Optional<EmailVerificationTokenEntity> findByToken(String token);
}
