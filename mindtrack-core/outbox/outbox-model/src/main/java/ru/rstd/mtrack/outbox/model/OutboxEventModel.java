package ru.rstd.mtrack.outbox.model;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import ru.rstd.mtrack.common.model.audit.Auditable;

import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
@Setter
@Accessors(chain = true)
public class OutboxEventModel extends Auditable {
    private OutboxEventType eventType;
    private Object payload;
    private OutboxEventStatus status;
    private Integer attempts;
    private OffsetDateTime processedAt;
    private String lastErrorMessage;

    public OutboxEventModel() {}

    public OutboxEventModel(UUID id,
                            OffsetDateTime createdAt,
                            OffsetDateTime updatedAt,
                            Integer attempts,
                            String lastErrorMessage,
                            OutboxEventType eventType,
                            OffsetDateTime processedAt,
                            Object payload,
                            OutboxEventStatus status) {
        super(id, createdAt, updatedAt);
        this.attempts = attempts;
        this.lastErrorMessage = lastErrorMessage;
        this.eventType = eventType;
        this.processedAt = processedAt;
        this.payload = payload;
        this.status = status;
    }
}
