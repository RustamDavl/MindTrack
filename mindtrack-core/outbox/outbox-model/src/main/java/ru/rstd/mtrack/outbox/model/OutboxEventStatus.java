package ru.rstd.mtrack.outbox.model;

public enum OutboxEventStatus {
    NEW,
    IN_PROGRESS,
    PROCESSED,
    FAILED
}
