package ru.rstd.mindly.rest.search;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import ru.rstd.mindly.input.search.note.NoteSearchService;


@RestController
@RequiredArgsConstructor
public class NoteSearchRestController {
    private final NoteSearchService noteSearchService;
}
