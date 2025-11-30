package com.example.api_livros.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object (DTO) para transferência de dados de Editora.
 * <p>
 * Esta classe é utilizada para receber e enviar informações de editoras através da API REST,
 * desacoplando a camada de apresentação da camada de modelo de dados.
 * </p>
 * 
 * @author @andreriffen
 * @version 1.0
 * @since 2025-11-30
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EditoraDTO {
    
    /**
     * Identificador único da editora.
     * Este campo é opcional em requisições de criação e obrigatório em respostas.
     */
    private Integer id;
    
    /**
     * Nome da editora.
     * Campo obrigatório e não pode ser vazio.
     */
    @NotBlank(message = "Nome é obrigatório")
    private String nome;
    
    /**
     * Endereço de email da editora.
     * Campo obrigatório e deve ter formato válido de email.
     */
    @Email(message = "Email deve ser válido")
    @NotBlank(message = "Email é obrigatório")
    private String email;
    
    /**
     * Cidade onde a editora está localizada.
     * Campo obrigatório.
     */
    @NotBlank(message = "Cidade é obrigatória")
    private String cidade;
    
    /**
     * Estado (UF) onde a editora está localizada.
     * Campo obrigatório.
     */
    @NotBlank(message = "Estado é obrigatório")
    private String estado;
}
