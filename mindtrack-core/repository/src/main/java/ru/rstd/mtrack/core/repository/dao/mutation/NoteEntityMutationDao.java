package ru.rstd.mtrack.core.repository.dao.mutation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.rstd.mtrack.core.api.mutation.note.NoteMutationDao;
import ru.rstd.mtrack.core.repository.entity.NoteEntity;
import ru.rstd.mtrack.core.repository.jpa.NoteEntityJpaRepository;
import ru.rstd.mtrack.core.model.note.Note;
import ru.rstd.mtrack.core.repository.mapper.NoteMapper;

@Repository
@RequiredArgsConstructor
public class NoteEntityMutationDao implements NoteMutationDao {
    private final NoteMapper mapper;
    private final NoteEntityJpaRepository noteEntityJpaRepository;

    @Override
    public Note save(Note note) {
        final NoteEntity noteEntity = mapper.toEntity(note);
        final NoteEntity savedNote = noteEntityJpaRepository.save(noteEntity);
        return mapper.toModel(savedNote);
    }
}
