package ru.rstd.mindly.model.note_tag;

import ru.rstd.mindly.model.base.BaseModel;
import ru.rstd.mindly.model.note.Note;
import ru.rstd.mindly.model.tag.Tag;

public class NoteTag extends BaseModel {
    private Note note;
    private Tag tag;
}
