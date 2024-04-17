package com.bci.api.user.exception;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalHandlerException {

    @ExceptionHandler(value = {ResourceNotFoundException.class})
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorResponse resourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
        return new ErrorResponse(ex.getMessage()+" - "+ request.getDescription(false));
    }

    @ExceptionHandler(value = {UnprocessableEntityException.class})
    @ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
    public ErrorResponse unprocessedEntityException(UnprocessableEntityException ex) {
        return new ErrorResponse(ex.getMessage());
    }

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorResponse constraintViolationException(MethodArgumentNotValidException ex) {
        String message = ex.getBindingResult().getFieldErrors().stream()
                .map(error -> error.getDefaultMessage() + " - Field: " + error.getField())
                .collect(Collectors.joining(" | "));
        return new ErrorResponse(message);
    }

    @ExceptionHandler(value = {AccessDeniedException.class})
    @ResponseStatus(value = HttpStatus.FORBIDDEN)
    public ErrorResponse accessDeniedException(AccessDeniedException ex) {
        return new ErrorResponse("Acceso denegado: " + ex.getMessage());
    }
}
