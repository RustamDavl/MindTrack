package ru.rstd.mindly.outbox.entity;

public enum OutboxEventEntityStatus {
    NEW,
    IN_PROGRESS,
    PROCESSING,
    FAILED
}
