package com.example.api_livros.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * Controlador global de exceções da API.
 * <p>
 * Esta classe intercepta e trata todas as exceções lançadas pelos controllers,
 * convertendo-as em respostas HTTP apropriadas com mensagens de erro padronizadas.
 * </p>
 * 
 * @author @andreriffen
 * @version 1.0
 * @since 2025-11-30
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Trata exceções de recurso não encontrado.
     * 
     * @param ex a exceção lançada
     * @param request a requisição HTTP que gerou a exceção
     * @return ResponseEntity contendo o erro e status HTTP 404 (NOT_FOUND)
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFoundException(
            ResourceNotFoundException ex, HttpServletRequest request) {
        
        ErrorResponse error = new ErrorResponse(
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                "Not Found",
                ex.getMessage(),
                request.getRequestURI()
        );
        
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    /**
     * Trata exceções de recurso duplicado.
     * 
     * @param ex a exceção lançada
     * @param request a requisição HTTP que gerou a exceção
     * @return ResponseEntity contendo o erro e status HTTP 409 (CONFLICT)
     */
    @ExceptionHandler(DuplicateResourceException.class)
    public ResponseEntity<ErrorResponse> handleDuplicateResourceException(
            DuplicateResourceException ex, HttpServletRequest request) {
        
        ErrorResponse error = new ErrorResponse(
                LocalDateTime.now(),
                HttpStatus.CONFLICT.value(),
                "Conflict",
                ex.getMessage(),
                request.getRequestURI()
        );
        
        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }

    /**
     * Trata exceções de validação de argumentos.
     * <p>
     * Captura erros de validação do Bean Validation (como @NotBlank, @Email, etc.)
     * e retorna um mapa com todos os campos inválidos e suas respectivas mensagens de erro.
     * </p>
     * 
     * @param ex a exceção de validação lançada
     * @param request a requisição HTTP que gerou a exceção
     * @return ResponseEntity contendo os erros de validação e status HTTP 400 (BAD_REQUEST)
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidationExceptions(
            MethodArgumentNotValidException ex, HttpServletRequest request) {
        
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        
        Map<String, Object> response = new HashMap<>();
        response.put("timestamp", LocalDateTime.now());
        response.put("status", HttpStatus.BAD_REQUEST.value());
        response.put("error", "Bad Request");
        response.put("message", "Erro de validação");
        response.put("errors", errors);
        response.put("path", request.getRequestURI());
        
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    /**
     * Trata todas as demais exceções não capturadas pelos outros handlers.
     * <p>
     * Este é o handler genérico que captura qualquer exceção não prevista,
     * evitando que detalhes internos sejam expostos ao cliente.
     * </p>
     * 
     * @param ex a exceção lançada
     * @param request a requisição HTTP que gerou a exceção
     * @return ResponseEntity contendo o erro e status HTTP 500 (INTERNAL_SERVER_ERROR)
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGlobalException(
            Exception ex, HttpServletRequest request) {
        
        ErrorResponse error = new ErrorResponse(
                LocalDateTime.now(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Internal Server Error",
                ex.getMessage(),
                request.getRequestURI()
        );
        
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
