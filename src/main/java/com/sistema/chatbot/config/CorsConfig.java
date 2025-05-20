package com.sistema.chatbot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        
        // Permitir credenciais
        config.setAllowCredentials(true);
        
        // Permitir origens específicas
        config.addAllowedOrigin("http://localhost:5173"); // Frontend URL
        config.addAllowedOrigin("http://localhost:3000"); // Frontend URL alternativa
        
        // Permitir todos os headers
        config.addAllowedHeader("*");
        
        // Permitir todos os métodos HTTP
        config.addAllowedMethod("*");
        
        // Configurar tempo máximo de cache do preflight
        config.setMaxAge(3600L);
        
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
} 