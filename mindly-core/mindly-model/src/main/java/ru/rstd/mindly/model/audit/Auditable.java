package ru.rstd.mindly.model.audit;

import lombok.Getter;
import lombok.Setter;
import ru.rstd.mindly.model.base.BaseModel;

import java.time.OffsetDateTime;

@Getter
@Setter
public abstract class Auditable extends BaseModel {
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;
}
