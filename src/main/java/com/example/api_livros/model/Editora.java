package com.example.api_livros.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entidade que representa uma editora de livros no sistema.
 * <p>
 * Esta classe mapeia a tabela "editoras" no banco de dados e contém as informações
 * sobre as editoras cadastradas, incluindo dados de contato e localização.
 * </p>
 * 
 * @author @andreriffen
 * @version 1.0
 * @since 2025-11-30
 */
@Entity
@Table(name = "editoras")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Editora {

    /**
     * Identificador único da editora.
     * Gerado automaticamente pelo banco de dados usando estratégia de auto incremento.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * Nome da editora.
     * Campo obrigatório e não pode ser vazio.
     */
    @NotBlank(message = "Nome é obrigatório")
    @Column(nullable = false)
    private String nome;

    /**
     * Endereço de email da editora.
     * Campo obrigatório, deve ser único no sistema e ter formato válido de email.
     */
    @Email(message = "Email deve ser válido")
    @NotBlank(message = "Email é obrigatório")
    @Column(nullable = false, unique = true)
    private String email;

    /**
     * Cidade onde a editora está localizada.
     * Campo obrigatório que indica a cidade sede da editora.
     */
    @NotBlank(message = "Cidade é obrigatória")
    @Column(nullable = false)
    private String cidade;

    /**
     * Estado (UF) onde a editora está localizada.
     * Campo obrigatório que indica o estado sede da editora.
     */
    @NotBlank(message = "Estado é obrigatório")
    @Column(nullable = false)
    private String estado;
}
