package ru.rstd.mtrack.core.dao.api.mutation.note;

import ru.rstd.mtrack.core.model.note.Note;

public interface NoteMutationDao {
    Note save(Note note);
}
