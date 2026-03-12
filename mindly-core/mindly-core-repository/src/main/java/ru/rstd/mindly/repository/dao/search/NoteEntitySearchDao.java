package ru.rstd.mindly.repository.dao.search;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.rstd.mindly.api.search.note.NoteSearchDao;
import ru.rstd.mindly.model.note.Note;
import ru.rstd.mindly.repository.NoteEntityJpaRepository;
import ru.rstd.mindly.repository.entity.NoteEntity;
import ru.rstd.mindly.repository.mapper.Mapper;

import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class NoteEntitySearchDao implements NoteSearchDao {
    private final Mapper<NoteEntity, Note> mapper;
    private final NoteEntityJpaRepository noteEntityJpaRepository;
    @Override
    public Optional<Note> find(UUID id) {
        return noteEntityJpaRepository
                .findById(id)
                .map(mapper::toModel);
    }
}
