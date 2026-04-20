package ru.rstd.mindly.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.rstd.mindly.repository.entity.TagEntity;

import java.util.UUID;

public interface TagEntityJpaRepository extends JpaRepository<TagEntity, UUID> {
}
