package ru.rstd.mindly.rest.mutation;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.rstd.mindly.input.mutation.note.NoteMutationService;
import ru.rstd.mindly.rest.api.NoteMutationRestApi;
import ru.rstd.mindly.rest.dto.NoteCreateRequest;

@RestController
@RequiredArgsConstructor
public class NoteMutationRestController implements NoteMutationRestApi {
    private final NoteMutationService noteMutationService;

    @Override
    public ResponseEntity<String> createNote(NoteCreateRequest noteCreateRequest) {
        return null;
    }
}
