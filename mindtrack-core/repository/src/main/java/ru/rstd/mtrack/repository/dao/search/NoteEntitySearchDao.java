package ru.rstd.mtrack.repository.dao.search;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.rstd.mtrack.api.search.note.NoteSearchDao;
import ru.rstd.mtrack.model.note.Note;
import ru.rstd.mtrack.repository.jpa.NoteEntityJpaRepository;
import ru.rstd.mtrack.repository.mapper.NoteMapper;

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
