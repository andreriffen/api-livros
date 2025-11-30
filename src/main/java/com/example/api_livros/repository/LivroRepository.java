package com.example.api_livros.repository;

import com.example.api_livros.model.Livro;
import com.example.api_livros.model.StatusLivro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repositório para operações de acesso a dados da entidade Livro.
 * <p>
 * Esta interface estende {@link JpaRepository} fornecendo métodos CRUD padrão
 * e métodos de consulta personalizados para a entidade {@link Livro}.
 * </p>
 * 
 * @author @andreriffen
 * @version 1.0
 * @since 2025-11-30
 */
@Repository
public interface LivroRepository extends JpaRepository<Livro, Integer> {
    
    /**
     * Busca um livro pelo código ISBN.
     * 
     * @param isbn o código ISBN a ser pesquisado
     * @return um {@link Optional} contendo o livro se encontrado, ou vazio caso contrário
     */
    Optional<Livro> findByIsbn(String isbn);
    
    /**
     * Verifica se existe um livro com o ISBN especificado.
     * 
     * @param isbn o código ISBN a ser verificado
     * @return {@code true} se existe um livro com o ISBN, {@code false} caso contrário
     */
    boolean existsByIsbn(String isbn);
    
    /**
     * Busca todos os livros com o status especificado.
     * 
     * @param status o status dos livros a serem buscados (DISPONIVEL, ESGOTADO, BLOQUEADO)
     * @return uma lista de livros com o status especificado
     */
    List<Livro> findByStatus(StatusLivro status);
    
    /**
     * Busca todos os livros de um autor específico.
     * 
     * @param autorId o identificador do autor
     * @return uma lista de livros do autor especificado
     */
    List<Livro> findByAutorId(Integer autorId);
    
    /**
     * Busca todos os livros de uma editora específica.
     * 
     * @param editoraId o identificador da editora
     * @return uma lista de livros da editora especificada
     */
    List<Livro> findByEditoraId(Integer editoraId);
}
