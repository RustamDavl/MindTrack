package ru.rstd.mindly.security.jpa;


import org.springframework.data.jpa.repository.JpaRepository;
import ru.rstd.mindly.security.entity.UserEntity;

import java.util.Optional;
import java.util.UUID;

public interface UserEntityJpaRepository extends JpaRepository<UserEntity, UUID> {
    Optional<UserEntity> findByEmail(String email);
}
