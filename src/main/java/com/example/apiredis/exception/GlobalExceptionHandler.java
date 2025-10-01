package com.example.apiredis.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

/**
 * Tratador Global de Exceções (@ControllerAdvice).
 * Captura exceções lançadas em toda a aplicação e retorna respostas HTTP formatadas.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Objeto auxiliar para padronizar o corpo da resposta de erro para 404/500.
     */
    public static class ErrorDetails {
        private String message;
        private String details;

        public ErrorDetails(String message, String details) {
            this.message = message;
            this.details = details;
        }

        public String getMessage() { return message; }
        public String getDetails() { return details; }
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleResourceNotFoundException(ResourceNotFoundException ex) {
        ErrorDetails errorDetails = new ErrorDetails(
                "Recurso não encontrado",
                ex.getMessage() // A mensagem customizada (ex: "Notícia não encontrada...")
        );
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND); // Retorna HTTP 404
    }

    /**
     * 2. Trata erros de validação (@Valid e @NotBlank) no Controller (400 Bad Request).
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(
            MethodArgumentNotValidException ex) {

        Map<String, String> errors = new HashMap<>();

        // Coleta todos os erros de campo
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage())
        );

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST); // Retorna HTTP 400
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> handleGlobalException(Exception ex) {
        ErrorDetails errorDetails = new ErrorDetails(
                "Ocorreu um erro interno no servidor",
                ex.getLocalizedMessage()
        );
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR); // Retorna HTTP 500
    }
}