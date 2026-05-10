package ru.rstd.mtrack.common.entity;

import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
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

    @PrePersist
    public void setCreatedAndUpdatedAt() {
        this.createdAt = OffsetDateTime.now();
        this.updatedAt = OffsetDateTime.now();
    }

    @PreUpdate
    public void setUpdatedAt() {
        this.updatedAt = OffsetDateTime.now();
    }
}
