package ru.rstd.mtrack.exception;

public class NoteNotFoundException extends ModelNotFoundException {

    public NoteNotFoundException(String message) {
        super(message);
    }

    public NoteNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
