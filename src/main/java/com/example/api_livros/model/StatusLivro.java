package com.example.api_livros.model;

/**
 * Enumeração que define os possíveis status de um livro no sistema.
 * <p>
 * Esta enum é utilizada para controlar a disponibilidade e o estado dos livros
 * no catálogo da aplicação.
 * </p>
 * 
 * @author @andreriffen
 * @version 1.0
 * @since 2025-11-30
 */
public enum StatusLivro {
    
    /**
     * Indica que o livro está disponível para venda no sistema.
     */
    DISPONIVEL,
    
    /**
     * Indica que o livro está temporariamente sem estoque.
     */
    ESGOTADO,
    
    /**
     * Indica que o livro foi bloqueado e não pode ser comercializado.
     * Utilizado para livros fora de catálogo ou com restrições.
     */
    BLOQUEADO
}
