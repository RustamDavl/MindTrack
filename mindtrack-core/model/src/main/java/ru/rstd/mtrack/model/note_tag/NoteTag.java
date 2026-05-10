package ru.rstd.mtrack.model.note_tag;

import ru.rstd.mtrack.common.model.base.BaseModel;
import ru.rstd.mtrack.model.note.Note;
import ru.rstd.mtrack.model.tag.Tag;

public class NoteTag extends BaseModel {
    private Note note;
    private Tag tag;
}
