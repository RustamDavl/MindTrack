package ru.rstd.mindly.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.rstd.mindly.repository.entity.NoteEntity;

import java.util.UUID;

public interface NoteEntityJpaRepository extends JpaRepository<NoteEntity, UUID> {
}
