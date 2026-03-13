package ru.rstd.mindly.rest.search;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.rstd.mindly.input.search.note.NoteSearchService;
import ru.rstd.mindly.model.note.Note;
import ru.rstd.mindly.rest.api.NoteSearchRestApi;
import ru.rstd.mindly.rest.dto.NoteReadResponse;
import ru.rstd.mindly.rest.mapper.ReadMapper;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class NoteSearchRestController implements NoteSearchRestApi {
    private final ReadMapper<Note, NoteReadResponse> mapper;
    private final NoteSearchService noteSearchService;

    @Override
    public ResponseEntity<NoteReadResponse> getNoteById(UUID id) {
        Note note = noteSearchService.find(id);
        return ResponseEntity.ok(mapper.toReadDto(note));
    }
}
