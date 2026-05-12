package ru.rstd.mtrack.core.rest.exception_handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import ru.rstd.mtrack.core.security.model.exception.MailVerificationException;

@RestControllerAdvice
public class RestControllerExceptionHandler {

    @ExceptionHandler(HttpClientErrorException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ExceptionResponse handleHttpClientErrorException(HttpClientErrorException e) {
        ExceptionResponse response = new ExceptionResponse();
        response.add(ExceptionResponse.FieldError.of("statusCode", e.getStatusCode().toString()));
        return response;
    }

    @ExceptionHandler(HttpServerErrorException.class)
    public ExceptionResponse handleHttpServerErrorException(HttpServerErrorException e) {
        return new ExceptionResponse(e.getMessage());
    }

    @ExceptionHandler(MailVerificationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionResponse handleMailVerificationException(MailVerificationException e) {
        return new ExceptionResponse(e.getMessage());
    }
}
