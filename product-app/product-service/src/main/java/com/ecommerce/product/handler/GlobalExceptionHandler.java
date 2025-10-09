package com.ecommerce.product.handler;

import lombok.extern.slf4j.Slf4j;
import org.ecommerce.exception.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.Map;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler
{
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleEntityNotFoundException(final EntityNotFoundException ex)
    {
        final Map<String, Object> error = Map
                .of("errors",
                    List.of(Map.of(
                        "status", "404",
                        "title", "Not Found",
                        "detail", ex.getMessage() != null ? ex.getMessage() : "The entity does not exist"
                    )));

        log.error("[EntityNotFoundException] exception: {}", ex.getMessage(), ex);

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    /**
     * Maneja excepciones lanzadas manualmente (ej. IllegalArgumentException).
     * Retorna una respuesta JSON:API con un único objeto de error.
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, Object>> handleIllegalArgumentException(final IllegalArgumentException ex)
    {
        final Map<String, Object> error = Map.of(
                "errors",
                List.of(createSingleError("400", "Invalid Argument", ex.getMessage()))
        );

        log.error("[IllegalArgumentException] exception: {}", ex.getMessage(), ex);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    private Map<String, Object> createSingleError(final String status, final String title, final String detail) {
        return Map.of("errors", List.of(Map.of("status", status, "title", title, "detail", detail)));
    }
}
