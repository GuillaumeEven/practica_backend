package es.ediae.master.programacion.gestionusuario.exception;

import java.time.Instant;
import java.util.List;

/**
 * Standard API error payload.
 */
public record ApiError(
        Instant timestamp,
        int status,
        String error,
        String message,
        String path,
        String code,
        List<FieldValidationError> errors,
        String traceId) {
}