package ru.rstd.mindly.repository.entity;

import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.UUID;

@MappedSuperclass
@Getter
@Setter
public abstract class AuditableEntity extends BaseEntity {

    public AuditableEntity() {
    }

    public AuditableEntity(UUID id, OffsetDateTime createdAt, OffsetDateTime updatedAt) {
        super(id);
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;
}
