package ru.rstd.mindly.security.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.rstd.mindly.security.entity.EmailVerificationTokenEntity;

import java.util.Optional;
import java.util.UUID;

public interface EmailVerificationTokenJpaRepository extends JpaRepository<EmailVerificationTokenEntity, UUID> {
    Optional<EmailVerificationTokenEntity> findByToken(String token);
}
