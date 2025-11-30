package com.example.api_livros.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Classe de configuração do ModelMapper.
 * <p>
 * Define o bean do ModelMapper que é utilizado para converter automaticamente
 * entre entidades JPA e DTOs, simplificando o mapeamento de objetos.
 * </p>
 * 
 * @author @andreriffen
 * @version 1.0
 * @since 2025-11-30
 */
@Configuration
public class ModelMapperConfig {

    /**
     * Cria e configura uma instância do ModelMapper.
     * <p>
     * O ModelMapper é uma biblioteca que automatiza o processo de mapeamento
     * de propriedades entre objetos, reduzindo código boilerplate.
     * </p>
     * 
     * @return uma instância configurada do ModelMapper
     */
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
