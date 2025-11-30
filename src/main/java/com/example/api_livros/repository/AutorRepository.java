package com.example.api_livros.repository;

import com.example.api_livros.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repositório para operações de acesso a dados da entidade Autor.
 * <p>
 * Esta interface estende {@link JpaRepository} fornecendo métodos CRUD padrão
 * e métodos de consulta personalizados para a entidade {@link Autor}.
 * </p>
 * 
 * @author @andreriffen
 * @version 1.0
 * @since 2025-11-30
 */
@Repository
public interface AutorRepository extends JpaRepository<Autor, Integer> {
    
    /**
     * Busca um autor pelo endereço de email.
     * 
     * @param email o endereço de email a ser pesquisado
     * @return um {@link Optional} contendo o autor se encontrado, ou vazio caso contrário
     */
    Optional<Autor> findByEmail(String email);
    
    /**
     * Verifica se existe um autor com o email especificado.
     * 
     * @param email o endereço de email a ser verificado
     * @return {@code true} se existe um autor com o email, {@code false} caso contrário
     */
    boolean existsByEmail(String email);
}
