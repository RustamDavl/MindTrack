package ru.rstd.mindly.input.search.note;

import ru.rstd.mindly.model.note.Note;

import java.util.UUID;

public interface NoteSearchService {
    Note find(UUID id);
}
