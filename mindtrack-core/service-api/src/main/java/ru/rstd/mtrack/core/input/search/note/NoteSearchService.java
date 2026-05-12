package ru.rstd.mtrack.core.input.search.note;

import ru.rstd.mtrack.core.model.note.Note;

import java.util.UUID;

public interface NoteSearchService {
    Note find(UUID id);
}
