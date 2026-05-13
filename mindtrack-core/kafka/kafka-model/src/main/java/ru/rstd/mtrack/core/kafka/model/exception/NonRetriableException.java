package ru.rstd.mtrack.core.kafka.model.exception;

public class NonRetriableException extends RuntimeException {
    public NonRetriableException() {}
    public NonRetriableException(String message) {
        super(message);
    }
}
