package ru.rstd.mtrack.common.model.audit;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import ru.rstd.mtrack.common.model.base.BaseModel;

import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
@Setter
@Accessors(chain = true)
@ToString(callSuper = true)
public abstract class Auditable extends BaseModel {
    public Auditable() {}

    public Auditable(UUID id, OffsetDateTime createdAt, OffsetDateTime updatedAt) {
        super(id);
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;
}
