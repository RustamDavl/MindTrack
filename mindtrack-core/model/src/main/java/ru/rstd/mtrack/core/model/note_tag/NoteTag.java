package ru.rstd.mtrack.core.model.note_tag;

import ru.rstd.mtrack.core.common.model.base.BaseModel;
import ru.rstd.mtrack.core.model.note.Note;
import ru.rstd.mtrack.core.model.tag.Tag;

public class NoteTag extends BaseModel {
    private Note note;
    private Tag tag;
}
