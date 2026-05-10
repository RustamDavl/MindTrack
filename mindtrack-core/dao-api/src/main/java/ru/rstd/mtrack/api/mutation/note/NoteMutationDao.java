package ru.rstd.mtrack.api.mutation.note;

import ru.rstd.mtrack.model.note.Note;

public interface NoteMutationDao {
    Note save(Note note);
}
