package ru.rstd.mtrack.core.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.rstd.mtrack.core.repository.entity.FolderEntity;

import java.util.UUID;

public interface FolderEntityJpaRepository extends JpaRepository<FolderEntity, UUID> {
}
