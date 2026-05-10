package ru.rstd.mtrack.rest.search;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.rstd.mtrack.input.search.note.NoteSearchService;
import ru.rstd.mtrack.model.note.Note;
import ru.rstd.mtrack.rest.api.NoteSearchRestApi;
import ru.rstd.mtrack.rest.dto.NoteReadResponse;
import ru.rstd.mtrack.rest.mapper.ReadMapper;

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
