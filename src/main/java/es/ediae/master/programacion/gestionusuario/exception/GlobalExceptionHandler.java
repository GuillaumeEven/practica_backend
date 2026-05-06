package es.ediae.master.programacion.gestionusuario.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(GeneralException.class)
    public ResponseEntity<Object> handleGeneralException(GeneralException ex, WebRequest request) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Error-Code", String.valueOf(ex.getCodigoDeError()));
        headers.add("X-Error-Message", ex.getMensajeDeError());

        Map<String, Object> body = new HashMap<>();
        body.put("code", ex.getCodigoDeError());
        body.put("message", ex.getMensajeDeError());

        // You can map codigoDeError to an HttpStatus if needed. Using BAD_REQUEST as default.
        return new ResponseEntity<>(body, headers, HttpStatus.BAD_REQUEST);
    }
}
