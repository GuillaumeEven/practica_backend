package es.ediae.master.programacion.gestionusuario.exception;

import java.time.Instant;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolationException;

/**
 * Centralized exception handler converting exceptions to a consistent ApiError payload.
 */
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    private String getPath(WebRequest request) {
        if (request instanceof ServletWebRequest sw) {
            try {
                return sw.getRequest().getRequestURI();
            } catch (Exception ignore) { }
        }
        return request.getDescription(false);
    }

    private String getTraceId(WebRequest request) {
        if (request instanceof ServletWebRequest sw) {
            String t = sw.getRequest().getHeader("X-Request-Id");
            return (t != null && !t.isBlank()) ? t : UUID.randomUUID().toString();
        }
        return UUID.randomUUID().toString();
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request) {

        List<FieldValidationError> errors = ex.getBindingResult().getFieldErrors().stream()
                .map(fe -> new FieldValidationError(fe.getField(), fe.getRejectedValue(), fe.getDefaultMessage()))
                .collect(Collectors.toList());

        String reason = (status instanceof HttpStatus hs) ? hs.getReasonPhrase() : "Bad Request";
        ApiError body = new ApiError(Instant.now(), status.value(), reason,
                "Validation failed", getPath(request), "VALIDATION_ERROR", errors, getTraceId(request));

        return ResponseEntity.status(status).headers(headers).body(body);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    protected ResponseEntity<Object> handleConstraintViolation(ConstraintViolationException ex, WebRequest request) {
        List<FieldValidationError> errors = ex.getConstraintViolations().stream()
                .map(cv -> new FieldValidationError(
                        cv.getPropertyPath() != null ? cv.getPropertyPath().toString() : null,
                        cv.getInvalidValue(),
                        cv.getMessage()))
                .collect(Collectors.toList());

        HttpStatus status = HttpStatus.BAD_REQUEST;
        ApiError body = new ApiError(Instant.now(), status.value(), status.getReasonPhrase(),
                "Validation failed", getPath(request), "VALIDATION_ERROR", errors, getTraceId(request));
        return ResponseEntity.status(status).body(body);
    }

    @ExceptionHandler({EntityNotFoundException.class, NoSuchElementException.class})
    protected ResponseEntity<Object> handleNotFound(RuntimeException ex, WebRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        ApiError body = new ApiError(Instant.now(), status.value(), status.getReasonPhrase(),
                ex.getMessage(), getPath(request), "NOT_FOUND", null, getTraceId(request));
        return ResponseEntity.status(status).body(body);
    }

    @ExceptionHandler(GeneralException.class)
    protected ResponseEntity<Object> handleGeneralException(GeneralException ex, WebRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ApiError body = new ApiError(Instant.now(), status.value(), status.getReasonPhrase(),
                ex.getMensajeDeError(), getPath(request), String.valueOf(ex.getCodigoDeError()), null, getTraceId(request));
        return ResponseEntity.status(status).body(body);
    }

    @ExceptionHandler(ResponseStatusException.class)
    protected ResponseEntity<Object> handleResponseStatus(ResponseStatusException ex, WebRequest request) {
        HttpStatusCode status = ex.getStatusCode();
        String reason = (status instanceof HttpStatus hs) ? hs.getReasonPhrase() : ex.getReason();
        ApiError body = new ApiError(Instant.now(), status.value(), reason,
                ex.getReason(), getPath(request), "ERROR", null, getTraceId(request));
        return ResponseEntity.status(status).body(body);
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<Object> handleAll(Exception ex, WebRequest request) {
        log.error("Unhandled exception", ex);
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        ApiError body = new ApiError(Instant.now(), status.value(), status.getReasonPhrase(),
                "Internal server error", getPath(request), "INTERNAL_ERROR", null, getTraceId(request));
        return ResponseEntity.status(status).body(body);
    }
}