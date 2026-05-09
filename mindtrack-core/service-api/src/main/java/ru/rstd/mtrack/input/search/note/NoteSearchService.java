package ru.rstd.mtrack.input.search.note;

import ru.rstd.mtrack.model.note.Note;

import java.util.UUID;

public interface NoteSearchService {
    Note find(UUID id);
}
