package ru.rstd.mtrack.core.input.mutation.note;

import ru.rstd.mtrack.core.model.note.Note;

public interface NoteMutationService {
    Note save(Note note);
}
