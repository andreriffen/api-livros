package com.example.api_livros.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entidade que representa um autor de livros no sistema.
 * <p>
 * Esta classe mapeia a tabela "autores" no banco de dados e contém as informações
 * básicas sobre os autores cadastrados no sistema.
 * </p>
 * 
 * @author @andreriffen
 * @version 1.0
 * @since 2025-11-30
 */
@Entity
@Table(name = "autores")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Autor {

    /**
     * Identificador único do autor.
     * Gerado automaticamente pelo banco de dados usando estratégia de auto incremento.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * Nome completo do autor.
     * Campo obrigatório e não pode ser vazio.
     */
    @NotBlank(message = "Nome é obrigatório")
    @Column(nullable = false)
    private String nome;

    /**
     * Endereço de email do autor.
     * Campo obrigatório, deve ser único no sistema e ter formato válido de email.
     */
    @Email(message = "Email deve ser válido")
    @NotBlank(message = "Email é obrigatório")
    @Column(nullable = false, unique = true)
    private String email;

    /**
     * Nacionalidade do autor.
     * Campo obrigatório que indica o país de origem do autor.
     */
    @NotBlank(message = "Nacionalidade é obrigatória")
    @Column(nullable = false)
    private String nacionalidade;
}
