package ru.rstd.mtrack.core.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.rstd.mtrack.core.repository.entity.NoteEntity;

import java.util.UUID;

public interface NoteEntityJpaRepository extends JpaRepository<NoteEntity, UUID> {
}
