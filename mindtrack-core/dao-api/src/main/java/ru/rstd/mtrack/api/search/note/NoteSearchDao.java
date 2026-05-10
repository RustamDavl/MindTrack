package ru.rstd.mtrack.api.search.note;

import ru.rstd.mtrack.model.note.Note;

import java.util.Optional;
import java.util.UUID;

public interface NoteSearchDao {
    Optional<Note> find(UUID id);
}
