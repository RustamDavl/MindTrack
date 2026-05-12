package ru.rstd.mtrack.core.api.search.note;

import ru.rstd.mtrack.core.model.note.Note;

import java.util.Optional;
import java.util.UUID;

public interface NoteSearchDao {
    Optional<Note> find(UUID id);
}
