package ru.rstd.mtrack.core.service.api.mutation.note;

import ru.rstd.mtrack.core.model.note.Note;

import java.util.UUID;

public interface NoteMutationService {
    Note save(Note note);
    Note update(Note note);
    boolean delete(UUID noteId);
}
