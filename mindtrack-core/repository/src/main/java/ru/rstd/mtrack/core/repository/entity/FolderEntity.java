package ru.rstd.mtrack.core.repository.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import ru.rstd.mtrack.core.common.entity.AuditableEntity;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(schema = "mtrack", name = "folder")
public class FolderEntity extends AuditableEntity {

    public FolderEntity() {
    }

    public FolderEntity(UUID id,
                        OffsetDateTime createdAt,
                        OffsetDateTime updatedAt,
                        String name, String color) {
        super(id, createdAt, updatedAt);
        this.name = name;
        this.color = color;
    }

    private String name;
    private String color;

    @OneToMany(mappedBy = "folder")
    private List<NoteEntity> notes;
}
