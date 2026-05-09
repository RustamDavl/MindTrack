package ru.rstd.mtrack.rest.mutation;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.rstd.mtrack.input.mutation.note.NoteMutationService;
import ru.rstd.mtrack.model.note.Note;
import ru.rstd.mtrack.rest.api.NoteMutationRestApi;
import ru.rstd.mtrack.rest.dto.NoteCreateRequest;
import ru.rstd.mtrack.rest.mapper.CreateMapper;

@RestController
@RequiredArgsConstructor
public class NoteMutationRestController implements NoteMutationRestApi {
    private final CreateMapper<NoteCreateRequest, Note> noteCreateMapper;
    private final NoteMutationService noteMutationService;

    @Override
    public ResponseEntity<String> createNote(NoteCreateRequest noteCreateRequest) {
        Note savedNote = noteMutationService.save(noteCreateMapper.toModel(noteCreateRequest));
        return ResponseEntity.ok(savedNote.getId().toString());
    }
}
