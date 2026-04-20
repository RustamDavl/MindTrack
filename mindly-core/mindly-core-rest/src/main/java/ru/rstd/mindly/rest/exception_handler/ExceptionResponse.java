package ru.rstd.mindly.rest.exception_handler;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class ExceptionResponse {

    @Setter
    private String message;
    private List<FieldError> fieldErrors;

    public ExceptionResponse() {
        this.fieldErrors = new ArrayList<>();
    }

    public ExceptionResponse(String message) {
        this.message = message;
        this.fieldErrors = new ArrayList<>();
    }

    public void add(FieldError fieldError) {
        this.fieldErrors.add(fieldError);
    }

    @AllArgsConstructor
    @Getter
    public static class FieldError {
        private String field;
        private String message;

        public static FieldError of(String field, String message) {
            return new FieldError(field, message);
        }
    }
}
