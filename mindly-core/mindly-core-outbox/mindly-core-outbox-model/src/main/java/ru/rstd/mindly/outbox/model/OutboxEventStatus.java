package ru.rstd.mindly.outbox.model;

public enum OutboxEventStatus {
    NEW,
    IN_PROGRESS,
    PROCESSED,
    FAILED
}
