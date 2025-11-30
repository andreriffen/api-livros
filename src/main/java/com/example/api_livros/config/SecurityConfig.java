package com.example.api_livros.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Classe de configuração de segurança da aplicação.
 * <p>
 * Define as políticas de segurança da API, incluindo proteção CSRF,
 * configurações de frame options e autorização de requisições.
 * </p>
 * <p>
 * <strong>Atenção:</strong> A configuração atual desabilita CSRF e permite
 * acesso público a todos os endpoints, adequado para desenvolvimento mas
 * requer ajustes para ambientes de produção.
 * </p>
 * 
 * @author @andreriffen
 * @version 1.0
 * @since 2025-11-30
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    /**
     * Configura a cadeia de filtros de segurança da aplicação.
     * <p>
     * Configurações aplicadas:
     * <ul>
     *   <li>CSRF desabilitado (adequado para APIs REST stateless)</li>
     *   <li>Frame Options configurado para permitir mesma origem (para uso com H2 Console)</li>
     *   <li>Todas as requisições permitidas sem autenticação</li>
     * </ul>
     * </p>
     * 
     * @param http o objeto HttpSecurity para configuração
     * @return a cadeia de filtros de segurança configurada
     * @throws Exception se ocorrer erro na configuração
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .headers(headers -> headers
                .frameOptions(frame -> frame.sameOrigin())
            )
            .authorizeHttpRequests(auth -> auth
                .anyRequest().permitAll()
            );
        
        return http.build();
    }
}
