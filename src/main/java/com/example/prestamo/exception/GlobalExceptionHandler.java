package com.example.prestamo.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // Errores que lanzamos en el service (404, 400, etc.)
    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<Map<String, Object>> manejarErrorDeNegocio(ResponseStatusException ex) {
        return construir(ex.getStatusCode().value(), ex.getReason());
    }

    // JSON mal formado o fecha con formato incorrecto
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Map<String, Object>> manejarJsonInvalido(HttpMessageNotReadableException ex) {
        return construir(400, "JSON inválido. Revisa los campos y que la fecha tenga formato AAAA-MM-DD");
    }

    private ResponseEntity<Map<String, Object>> construir(int status, String mensaje) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("status", status);
        body.put("message", mensaje);
        return ResponseEntity.status(status).body(body);
    }
}
