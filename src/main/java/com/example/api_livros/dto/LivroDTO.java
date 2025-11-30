package com.example.api_livros.dto;

import com.example.api_livros.model.StatusLivro;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Data Transfer Object (DTO) para transferência de dados de Livro.
 * <p>
 * Esta classe é utilizada para receber e enviar informações de livros através da API REST,
 * desacoplando a camada de apresentação da camada de modelo de dados.
 * Inclui campos para relacionamentos com autor e editora, enviando apenas IDs para criação/atualização
 * e retornando também os nomes nas respostas.
 * </p>
 * 
 * @author @andreriffen
 * @version 1.0
 * @since 2025-11-30
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LivroDTO {
    
    /**
     * Identificador único do livro.
     * Este campo é opcional em requisições de criação e obrigatório em respostas.
     */
    private Integer id;
    
    /**
     * Título do livro.
     * Campo obrigatório e não pode ser vazio.
     */
    @NotBlank(message = "Título é obrigatório")
    private String titulo;
    
    /**
     * Código ISBN (International Standard Book Number) do livro.
     * Campo obrigatório e não pode ser vazio.
     */
    @NotBlank(message = "ISBN é obrigatório")
    private String isbn;
    
    /**
     * Preço de custo do livro.
     * Campo obrigatório, deve ser um valor positivo e é utilizado para calcular o preço de venda.
     */
    @NotNull(message = "Preço de custo é obrigatório")
    @Positive(message = "Preço de custo deve ser positivo")
    private BigDecimal precoDeCusto;
    
    /**
     * Preço de venda do livro.
     * Calculado automaticamente pelo sistema e retornado nas respostas.
     * Não deve ser enviado em requisições de criação ou atualização.
     */
    private BigDecimal precoDeVenda;
    
    /**
     * Margem de lucro aplicada ao livro (valor decimal).
     * Campo obrigatório. Exemplo: 0.30 representa 30% de margem de lucro.
     */
    @NotNull(message = "Margem de lucro é obrigatória")
    private BigDecimal margemDeLucro;
    
    /**
     * Data de cadastro do livro no sistema.
     * Definida automaticamente pelo sistema e retornada nas respostas.
     */
    private LocalDate dataDeCadastro;
    
    /**
     * Status atual do livro no sistema.
     * Campo obrigatório. Valores possíveis: DISPONIVEL, ESGOTADO, BLOQUEADO.
     */
    @NotNull(message = "Status é obrigatório")
    private StatusLivro status;
    
    /**
     * Identificador do autor do livro.
     * Campo obrigatório para criação e atualização do livro.
     */
    @NotNull(message = "ID do autor é obrigatório")
    private Integer autorId;
    
    /**
     * Nome do autor do livro.
     * Campo somente leitura, retornado nas respostas para facilitar a visualização.
     */
    private String autorNome;
    
    /**
     * Identificador da editora do livro.
     * Campo obrigatório para criação e atualização do livro.
     */
    @NotNull(message = "ID da editora é obrigatório")
    private Integer editoraId;
    
    /**
     * Nome da editora do livro.
     * Campo somente leitura, retornado nas respostas para facilitar a visualização.
     */
    private String editoraNome;
}
