package ru.rstd.mtrack.core.kafka.model.exception;

public class RetriableException extends RuntimeException {
    public RetriableException() {
    }
    public RetriableException(String message) {
        super(message);
    }
}
