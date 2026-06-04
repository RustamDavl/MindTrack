package ru.rstd.mtrack.core.repository.mapper;

import org.springframework.stereotype.Component;
import ru.rstd.mtrack.core.common.mapper.Mapper;
import ru.rstd.mtrack.core.model.note.Note;
import ru.rstd.mtrack.core.repository.entity.NoteEntity;
import ru.rstd.mtrack.core.security.repository.entity.UserEntity;

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
        UserEntity user = new UserEntity();
        user.setId(model.getUserId());
        noteEntity.setUser(user);

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
        note.setUserId(entity.getUser().getId());

        return note;
    }
}
