package ru.rstd.mindly.rest.mutation;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.rstd.mindly.input.mutation.note.NoteMutationService;
import ru.rstd.mindly.model.note.Note;
import ru.rstd.mindly.rest.api.NoteMutationRestApi;
import ru.rstd.mindly.rest.dto.NoteCreateRequest;
import ru.rstd.mindly.rest.mapper.CreateMapper;

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
