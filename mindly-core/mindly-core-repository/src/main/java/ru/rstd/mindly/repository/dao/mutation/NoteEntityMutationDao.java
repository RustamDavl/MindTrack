package ru.rstd.mindly.repository.dao.mutation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.rstd.mindly.api.mutation.note.NoteMutationDao;
import ru.rstd.mindly.repository.entity.NoteEntity;
import ru.rstd.mindly.repository.jpa.NoteEntityJpaRepository;
import ru.rstd.mindly.model.note.Note;
import ru.rstd.mindly.repository.mapper.NoteMapper;

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
