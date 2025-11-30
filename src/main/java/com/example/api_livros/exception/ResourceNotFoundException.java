package com.example.api_livros.exception;

/**
 * Exceção lançada quando um recurso solicitado não é encontrado no sistema.
 * <p>
 * Esta exceção é utilizada para indicar que uma busca por ID, email ou outro
 * identificador não retornou nenhum resultado no banco de dados.
 * </p>
 * <p>
 * Quando capturada pelo {@link GlobalExceptionHandler}, retorna um status HTTP 404 (NOT_FOUND).
 * </p>
 * 
 * @author @andreriffen
 * @version 1.0
 * @since 2025-11-30
 */
public class ResourceNotFoundException extends RuntimeException {
    
    /**
     * Constrói uma nova exceção com a mensagem especificada.
     * 
     * @param message a mensagem de erro detalhada
     */
    public ResourceNotFoundException(String message) {
        super(message);
    }
    
    /**
     * Constrói uma nova exceção com uma mensagem formatada automaticamente.
     * <p>
     * Gera uma mensagem no formato: "[ResourceName] não encontrado(a) com [fieldName]: '[fieldValue]'"
     * </p>
     * 
     * @param resourceName o nome do recurso (ex: "Autor", "Editora", "Livro")
     * @param fieldName o nome do campo usado na busca (ex: "id", "email")
     * @param fieldValue o valor do campo que não foi encontrado
     */
    public ResourceNotFoundException(String resourceName, String fieldName, Object fieldValue) {
        super(String.format("%s não encontrado(a) com %s: '%s'", resourceName, fieldName, fieldValue));
    }
}
