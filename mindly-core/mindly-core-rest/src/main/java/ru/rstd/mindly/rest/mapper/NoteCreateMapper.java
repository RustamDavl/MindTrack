package ru.rstd.mindly.rest.mapper;

import org.springframework.stereotype.Component;
import ru.rstd.mindly.model.note.Note;
import ru.rstd.mindly.rest.dto.NoteCreateRequest;
import ru.rstd.mindly.rest.dto.NoteReadResponse;

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
