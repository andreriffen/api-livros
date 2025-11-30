package com.example.api_livros.exception;

/**
 * Exceção lançada quando se tenta criar um recurso que já existe no sistema.
 * <p>
 * Esta exceção é utilizada para indicar violações de unicidade, como tentar cadastrar
 * um autor ou editora com um email que já está em uso, ou um livro com ISBN duplicado.
 * </p>
 * <p>
 * Quando capturada pelo {@link GlobalExceptionHandler}, retorna um status HTTP 409 (CONFLICT).
 * </p>
 * 
 * @author @andreriffen
 * @version 1.0
 * @since 2025-11-30
 */
public class DuplicateResourceException extends RuntimeException {
    
    /**
     * Constrói uma nova exceção com a mensagem especificada.
     * 
     * @param message a mensagem de erro detalhada
     */
    public DuplicateResourceException(String message) {
        super(message);
    }
    
    /**
     * Constrói uma nova exceção com uma mensagem formatada automaticamente.
     * <p>
     * Gera uma mensagem no formato: "[ResourceName] já existe com [fieldName]: '[fieldValue]'"
     * </p>
     * 
     * @param resourceName o nome do recurso (ex: "Autor", "Editora", "Livro")
     * @param fieldName o nome do campo que viola a unicidade (ex: "email", "isbn")
     * @param fieldValue o valor do campo que já existe
     */
    public DuplicateResourceException(String resourceName, String fieldName, Object fieldValue) {
        super(String.format("%s já existe com %s: '%s'", resourceName, fieldName, fieldValue));
    }
}
