package ru.rstd.mtrack.core.repository.dao.search;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.rstd.mtrack.core.dao.api.search.note.NoteSearchDao;
import ru.rstd.mtrack.core.model.note.Note;
import ru.rstd.mtrack.core.repository.jpa.NoteEntityJpaRepository;
import ru.rstd.mtrack.core.repository.mapper.NoteMapper;

import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class NoteEntitySearchDao implements NoteSearchDao {
    private final NoteMapper mapper;
    private final NoteEntityJpaRepository noteEntityJpaRepository;
    @Override
    public Optional<Note> find(UUID id) {
        return noteEntityJpaRepository
                .findById(id)
                .map(mapper::toModel);
    }
}
