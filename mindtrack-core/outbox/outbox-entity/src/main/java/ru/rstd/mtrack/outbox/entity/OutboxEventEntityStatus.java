package ru.rstd.mtrack.outbox.entity;

public enum OutboxEventEntityStatus {
    NEW,
    IN_PROGRESS,
    PROCESSING,
    FAILED
}
