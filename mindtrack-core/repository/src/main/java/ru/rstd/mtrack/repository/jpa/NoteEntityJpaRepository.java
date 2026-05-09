package ru.rstd.mtrack.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.rstd.mtrack.repository.entity.NoteEntity;

import java.util.UUID;

public interface NoteEntityJpaRepository extends JpaRepository<NoteEntity, UUID> {
}
