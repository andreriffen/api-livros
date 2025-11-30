package com.example.api_livros.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object (DTO) para transferência de dados de Autor.
 * <p>
 * Esta classe é utilizada para receber e enviar informações de autores através da API REST,
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
public class AutorDTO {
    
    /**
     * Identificador único do autor.
     * Este campo é opcional em requisições de criação e obrigatório em respostas.
     */
    private Integer id;
    
    /**
     * Nome completo do autor.
     * Campo obrigatório e não pode ser vazio.
     */
    @NotBlank(message = "Nome é obrigatório")
    private String nome;
    
    /**
     * Endereço de email do autor.
     * Campo obrigatório e deve ter formato válido de email.
     */
    @Email(message = "Email deve ser válido")
    @NotBlank(message = "Email é obrigatório")
    private String email;
    
    /**
     * Nacionalidade do autor.
     * Campo obrigatório que indica o país de origem do autor.
     */
    @NotBlank(message = "Nacionalidade é obrigatória")
    private String nacionalidade;
}
