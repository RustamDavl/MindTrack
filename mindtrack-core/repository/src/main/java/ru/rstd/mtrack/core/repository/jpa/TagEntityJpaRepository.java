package ru.rstd.mtrack.core.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.rstd.mtrack.core.repository.entity.TagEntity;

import java.util.UUID;

public interface TagEntityJpaRepository extends JpaRepository<TagEntity, UUID> {
}
