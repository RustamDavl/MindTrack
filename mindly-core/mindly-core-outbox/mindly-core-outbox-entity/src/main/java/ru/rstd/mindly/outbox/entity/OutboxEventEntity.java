package ru.rstd.mindly.outbox.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcType;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.dialect.PostgreSQLEnumJdbcType;
import org.hibernate.type.SqlTypes;
import ru.rstd.mindly.common.entity.AuditableEntity;

import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(schema = "mindly", name = "outbox_event")
public class OutboxEventEntity extends AuditableEntity {
    public OutboxEventEntity() {
    }

    public OutboxEventEntity(UUID id,
                             OffsetDateTime createdAt,
                             OffsetDateTime updatedAt,
                             Integer attempts,
                             OutboxEventEntityType eventType,
                             String lastErrorMessage,
                             String payload,
                             OffsetDateTime processedAt,
                             OutboxEventEntityStatus status) {
        super(id, createdAt, updatedAt);
        this.attempts = attempts;
        this.eventType = eventType;
        this.lastErrorMessage = lastErrorMessage;
        this.payload = payload;
        this.processedAt = processedAt;
        this.status = status;
    }

    @Column(name = "event_type")
    @Enumerated(EnumType.STRING)
    private OutboxEventEntityType eventType;

    @JdbcTypeCode(SqlTypes.JSON)
    private String payload;

    @JdbcType(PostgreSQLEnumJdbcType.class)
    @Column(columnDefinition = "outbox_status")
    @Enumerated(EnumType.STRING)
    private OutboxEventEntityStatus status;

    private Integer attempts;

    @Column(name = "processed_at")
    private OffsetDateTime processedAt;

    @Column(name = "last_error_message")
    private String lastErrorMessage;
}
