package com.example.api_livros.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Entidade que representa um livro no sistema de gerenciamento de livros.
 * <p>
 * Esta classe mapeia a tabela "livros" no banco de dados e contém todas as informações
 * relacionadas aos livros, incluindo dados de precificação, relacionamentos com autor e editora,
 * e cálculo automático de preço de venda baseado na margem de lucro.
 * </p>
 * <p>
 * O preço de venda é calculado automaticamente através do método {@link #calcularPrecoVenda()}
 * que é executado antes de persistir ou atualizar a entidade no banco de dados.
 * </p>
 * 
 * @author @andreriffen
 * @version 1.0
 * @since 2025-11-30
 */
@Entity
@Table(name = "livros")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Livro {

    /**
     * Identificador único do livro.
     * Gerado automaticamente pelo banco de dados usando estratégia de auto incremento.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * Título do livro.
     * Campo obrigatório e não pode ser vazio.
     */
    @NotBlank(message = "Título é obrigatório")
    @Column(nullable = false)
    private String titulo;

    /**
     * Código ISBN (International Standard Book Number) do livro.
     * Campo obrigatório, único no sistema e não pode ser vazio.
     */
    @NotBlank(message = "ISBN é obrigatório")
    @Column(nullable = false, unique = true)
    private String isbn;

    /**
     * Preço de custo do livro.
     * Campo obrigatório, deve ser um valor positivo e é utilizado para calcular o preço de venda.
     */
    @NotNull(message = "Preço de custo é obrigatório")
    @Positive(message = "Preço de custo deve ser positivo")
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal precoDeCusto;

    /**
     * Preço de venda do livro.
     * Calculado automaticamente com base no preço de custo e margem de lucro.
     * Não deve ser definido manualmente.
     */
    @Column(precision = 10, scale = 2)
    private BigDecimal precoDeVenda;

    /**
     * Margem de lucro aplicada ao livro (valor decimal).
     * Campo obrigatório. Exemplo: 0.30 representa 30% de margem de lucro.
     */
    @NotNull(message = "Margem de lucro é obrigatória")
    @Column(nullable = false, precision = 5, scale = 2)
    private BigDecimal margemDeLucro;

    /**
     * Data de cadastro do livro no sistema.
     * Definida automaticamente no momento da criação do registro.
     */
    @Column(nullable = false)
    private LocalDate dataDeCadastro;

    /**
     * Status atual do livro no sistema.
     * Pode ser: DISPONIVEL, ESGOTADO ou BLOQUEADO.
     */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusLivro status;

    /**
     * Autor do livro.
     * Relacionamento many-to-one obrigatório com a entidade Autor.
     */
    @ManyToOne
    @JoinColumn(name = "autor_id", nullable = false)
    @NotNull(message = "Autor é obrigatório")
    private Autor autor;

    /**
     * Editora responsável pela publicação do livro.
     * Relacionamento many-to-one obrigatório com a entidade Editora.
     */
    @ManyToOne
    @JoinColumn(name = "editora_id", nullable = false)
    @NotNull(message = "Editora é obrigatória")
    private Editora editora;

    /**
     * Método executado antes de persistir um novo livro no banco de dados.
     * Define a data de cadastro como a data atual e calcula o preço de venda.
     */
    @PrePersist
    protected void onCreate() {
        dataDeCadastro = LocalDate.now();
        calcularPrecoVenda();
    }

    /**
     * Método executado antes de atualizar um livro existente no banco de dados.
     * Recalcula o preço de venda sempre que houver alterações.
     */
    @PreUpdate
    protected void onUpdate() {
        calcularPrecoVenda();
    }

    /**
     * Calcula o preço de venda baseado no preço de custo e margem de lucro.
     * <p>
     * A fórmula utilizada é:
     * <code>precoDeVenda = precoDeCusto + (precoDeCusto * margemDeLucro)</code>
     * </p>
     * <p>
     * Exemplo: Se o preço de custo for R$ 100,00 e a margem de lucro for 0.30 (30%),
     * o preço de venda será R$ 130,00.
     * </p>
     */
    public void calcularPrecoVenda() {
        if (precoDeCusto != null && margemDeLucro != null) {
            BigDecimal lucro = precoDeCusto.multiply(margemDeLucro);
            this.precoDeVenda = precoDeCusto.add(lucro);
        }
    }
}
