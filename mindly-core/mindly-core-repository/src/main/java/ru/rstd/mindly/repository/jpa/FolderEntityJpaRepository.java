package ru.rstd.mindly.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.rstd.mindly.repository.entity.FolderEntity;

import java.util.UUID;

public interface FolderEntityJpaRepository extends JpaRepository<FolderEntity, UUID> {
}
