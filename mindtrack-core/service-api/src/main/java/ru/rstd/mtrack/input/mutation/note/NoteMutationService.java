package ru.rstd.mtrack.input.mutation.note;

import ru.rstd.mtrack.model.note.Note;

public interface NoteMutationService {
    Note save(Note note);
}
