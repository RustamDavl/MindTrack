package ru.rstd.mtrack.core.rest.mapper;

import org.springframework.stereotype.Component;
import ru.rstd.mtrack.core.model.note.Note;
import ru.rstd.mtrack.core.rest.dto.NoteReadResponse;

@Component
public class NoteReadMapper implements ReadMapper<Note, NoteReadResponse> {

    @Override
    public NoteReadResponse toReadDto(Note note) {
        NoteReadResponse response = new NoteReadResponse();
        response.setId(note.getId());
        response.setTitle(note.getTitle());
        response.setBody(note.getBody());
        response.setCreatedAt(note.getCreatedAt());
        response.setColor(note.getColor());
        return response;
    }
}
