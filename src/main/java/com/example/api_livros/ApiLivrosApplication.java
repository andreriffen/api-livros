package com.example.api_livros;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Classe principal da aplicação API de Gerenciamento de Livros.
 * <p>
 * Esta é a classe de inicialização da aplicação Spring Boot que gerencia um catálogo
 * de livros, autores e editoras. A aplicação fornece uma API REST completa para
 * operações CRUD (Create, Read, Update, Delete) de todos os recursos.
 * </p>
 * <p>
 * Funcionalidades principais:
 * <ul>
 *   <li>Gerenciamento de autores</li>
 *   <li>Gerenciamento de editoras</li>
 *   <li>Gerenciamento de livros com cálculo automático de preço de venda</li>
 *   <li>Validação de dados de entrada</li>
 *   <li>Tratamento global de exceções</li>
 *   <li>Documentação OpenAPI/Swagger</li>
 * </ul>
 * </p>
 * 
 * @author @andreriffen
 * @version 1.0
 * @since 2025-11-30
 */
@SpringBootApplication
public class ApiLivrosApplication {

	/**
	 * Método principal que inicia a aplicação Spring Boot.
	 * 
	 * @param args argumentos de linha de comando passados para a aplicação
	 */
	public static void main(String[] args) {
		SpringApplication.run(ApiLivrosApplication.class, args);
	}

}
