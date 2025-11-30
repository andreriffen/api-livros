package com.example.api_livros.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Classe que representa a estrutura padrão de resposta de erro da API.
 * <p>
 * Esta classe é utilizada para padronizar as mensagens de erro retornadas pela API,
 * fornecendo informações detalhadas sobre o erro ocorrido.
 * </p>
 * 
 * @author @andreriffen
 * @version 1.0
 * @since 2025-11-30
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {
    
    /**
     * Data e hora em que o erro ocorreu.
     */
    private LocalDateTime timestamp;
    
    /**
     * Código de status HTTP do erro (ex: 404, 409, 500).
     */
    private int status;
    
    /**
     * Descrição breve do tipo de erro (ex: "Not Found", "Conflict").
     */
    private String error;
    
    /**
     * Mensagem detalhada explicando o erro ocorrido.
     */
    private String message;
    
    /**
     * Caminho da requisição HTTP que gerou o erro.
     */
    private String path;
}
