package ru.rstd.mtrack.core.outbox.model;

public enum OutboxEventStatus {
    NEW,
    IN_PROGRESS,
    PROCESSED,
    FAILED
}
