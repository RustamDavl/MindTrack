package ru.rstd.mtrack.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.rstd.mtrack.repository.entity.TagEntity;

import java.util.UUID;

public interface TagEntityJpaRepository extends JpaRepository<TagEntity, UUID> {
}
