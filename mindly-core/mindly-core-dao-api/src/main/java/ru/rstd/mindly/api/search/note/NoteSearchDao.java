package ru.rstd.mindly.api.search.note;

import ru.rstd.mindly.model.note.Note;

import java.util.Optional;
import java.util.UUID;

public interface NoteSearchDao {
    Optional<Note> find(UUID id);
}
