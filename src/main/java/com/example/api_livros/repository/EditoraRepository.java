package com.example.api_livros.repository;

import com.example.api_livros.model.Editora;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repositório para operações de acesso a dados da entidade Editora.
 * <p>
 * Esta interface estende {@link JpaRepository} fornecendo métodos CRUD padrão
 * e métodos de consulta personalizados para a entidade {@link Editora}.
 * </p>
 * 
 * @author @andreriffen
 * @version 1.0
 * @since 2025-11-30
 */
@Repository
public interface EditoraRepository extends JpaRepository<Editora, Integer> {
    
    /**
     * Busca uma editora pelo endereço de email.
     * 
     * @param email o endereço de email a ser pesquisado
     * @return um {@link Optional} contendo a editora se encontrada, ou vazio caso contrário
     */
    Optional<Editora> findByEmail(String email);
    
    /**
     * Verifica se existe uma editora com o email especificado.
     * 
     * @param email o endereço de email a ser verificado
     * @return {@code true} se existe uma editora com o email, {@code false} caso contrário
     */
    boolean existsByEmail(String email);
}
