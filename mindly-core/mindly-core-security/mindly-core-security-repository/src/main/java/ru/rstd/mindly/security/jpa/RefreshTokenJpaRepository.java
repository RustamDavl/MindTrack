package ru.rstd.mindly.security.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.rstd.mindly.security.entity.RefreshTokenEntity;

import java.util.Optional;
import java.util.UUID;

public interface RefreshTokenJpaRepository extends JpaRepository<RefreshTokenEntity, UUID> {
    Optional<RefreshTokenEntity> findByToken(String token);
}
