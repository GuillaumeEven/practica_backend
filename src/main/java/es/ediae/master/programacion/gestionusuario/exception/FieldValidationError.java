package es.ediae.master.programacion.gestionusuario.exception;

/**
 * Validation detail for a single field.
 */
public record FieldValidationError(String field, Object rejectedValue, String message) {
}