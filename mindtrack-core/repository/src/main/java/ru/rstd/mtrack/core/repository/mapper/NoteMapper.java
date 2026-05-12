package ru.rstd.mtrack.core.repository.mapper;

import org.springframework.stereotype.Component;
import ru.rstd.mtrack.core.common.model.mapper.Mapper;
import ru.rstd.mtrack.core.model.note.Note;
import ru.rstd.mtrack.core.repository.entity.NoteEntity;

@Component
public class NoteMapper implements Mapper<NoteEntity, Note> {

    @Override
    public NoteEntity toEntity(final Note model) {
        final NoteEntity noteEntity = new NoteEntity();
        noteEntity.setTitle(model.getTitle());
        noteEntity.setBody(model.getBody());
        noteEntity.setColor(model.getColor());
        noteEntity.setCreatedAt(model.getCreatedAt());
        noteEntity.setUpdatedAt(model.getUpdatedAt());

        return noteEntity;
    }

    @Override
    public Note toModel(final NoteEntity entity) {
        final Note note = new Note();
        note.setTitle(entity.getTitle());
        note.setBody(entity.getBody());
        note.setColor(entity.getColor());
        note.setId(entity.getId());
        note.setCreatedAt(entity.getCreatedAt());

        return note;
    }
}
