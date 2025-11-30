package com.example.api_livros.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

/**
 * Classe de configuração CORS (Cross-Origin Resource Sharing) da aplicação.
 * <p>
 * Define as políticas de compartilhamento de recursos entre origens diferentes,
 * permitindo que a API seja consumida por aplicações front-end hospedadas em domínios distintos.
 * </p>
 * <p>
 * <strong>Atenção:</strong> A configuração atual permite requisições de qualquer origem (*),
 * o que é adequado para desenvolvimento mas deve ser restringido em produção.
 * </p>
 * 
 * @author @andreriffen
 * @version 1.0
 * @since 2025-11-30
 */
@Configuration
public class CorsConfig {

    /**
     * Configura e registra o filtro CORS da aplicação.
     * <p>
     * Permite:
     * <ul>
     *   <li>Credenciais (cookies, autenticação HTTP)</li>
     *   <li>Qualquer origem (padrão *)</li>
     *   <li>Todos os cabeçalhos HTTP</li>
     *   <li>Métodos: GET, POST, PUT, DELETE, OPTIONS, PATCH</li>
     * </ul>
     * </p>
     * 
     * @return o filtro CORS configurado
     */
    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOriginPattern("*");
        config.setAllowedHeaders(Arrays.asList("*"));
        config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH"));
        
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        
        return new CorsFilter(source);
    }
}
