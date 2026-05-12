package ru.rstd.mtrack.core.rest.mapper;

import org.springframework.stereotype.Component;
import ru.rstd.mtrack.core.model.note.Note;
import ru.rstd.mtrack.core.rest.dto.NoteCreateRequest;

import java.time.OffsetDateTime;

@Component
public class NoteCreateMapper implements CreateMapper<NoteCreateRequest, Note> {

    @Override
    public Note toModel(NoteCreateRequest noteCreateRequest) {
        Note note = new Note();
        note.setCreatedAt(OffsetDateTime.now());
        note.setTitle(noteCreateRequest.getTitle());
        note.setBody(noteCreateRequest.getBody());
        note.setColor(noteCreateRequest.getColor());
        return note;
    }
}
