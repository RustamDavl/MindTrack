package ru.rstd.mtrack.core.repository.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import ru.rstd.mtrack.core.common.entity.AuditableEntity;

import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(schema = "mtrack", name = "note")
public class NoteEntity extends AuditableEntity {

    public NoteEntity() {
    }

    public NoteEntity(UUID id,
                      OffsetDateTime createdAt,
                      OffsetDateTime updatedAt,
                      String title,
                      String body,
                      String color) {
        super(id, createdAt, updatedAt);
        this.title = title;
        this.body = body;
        this.color = color;
    }

    private String title;
    private String body;
    private String color;

    @ManyToOne
    @JoinColumn(name = "folder_id")
    private FolderEntity folder;
}
