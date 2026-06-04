package ru.rstd.mtrack.core.dao.api.mutation.note;

import ru.rstd.mtrack.core.model.note.Note;

import java.util.UUID;

public interface NoteMutationDao {
    Note save(Note note);
    void delete(UUID noteId);
}
