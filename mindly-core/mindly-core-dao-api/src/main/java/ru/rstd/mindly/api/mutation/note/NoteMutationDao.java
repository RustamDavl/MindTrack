package ru.rstd.mindly.api.mutation.note;

import ru.rstd.mindly.model.note.Note;

public interface NoteMutationDao {
    Note save(Note note);
}
