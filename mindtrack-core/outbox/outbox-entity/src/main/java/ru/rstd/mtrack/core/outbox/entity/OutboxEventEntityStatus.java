package ru.rstd.mtrack.core.outbox.entity;

public enum OutboxEventEntityStatus {
    NEW,
    IN_PROGRESS,
    PROCESSING,
    FAILED
}
