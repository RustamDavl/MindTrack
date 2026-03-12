package ru.rstd.mindly.repository.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity
@Table(schema = "mindly", name = "note_tag")
public class NoteTagEntity extends BaseEntity {

    public NoteTagEntity() {
    }

    public NoteTagEntity(UUID id, NoteEntity note, TagEntity tag) {
        super(id);
        this.note = note;
        this.tag = tag;
    }

    @ManyToOne
    @JoinColumn(name = "note_id")
    private NoteEntity note;

    @ManyToOne
    @JoinColumn(name = "tag_id")
    private TagEntity tag;
}
