package com.sistema.chatbot.security;

import jakarta.servlet.Filter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfigurations {

    @Autowired
    SecurityFilter securityFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        // Acesso público
                        .requestMatchers(HttpMethod.POST, "/api/auth/**").permitAll()
                        .requestMatchers("/swagger-ui.html", "/swagger-ui/**", "/v3/api-docs/**").permitAll()
                        
                        // Configurações específicas para /api/sales
                        .requestMatchers(HttpMethod.GET, "/api/sales").hasAnyRole("ADMIN", "USER")
                        .requestMatchers(HttpMethod.GET, "/api/sales/**").hasAnyRole("ADMIN", "USER")
                        .requestMatchers(HttpMethod.POST, "/api/sales").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/sales/**").hasRole("ADMIN")
                        
                        // Configurações para outros endpoints
                        .requestMatchers("/api/products").permitAll()
                        .requestMatchers("/api/reports").hasRole("ADMIN")
                        .requestMatchers("/api/users").hasRole("ADMIN")
                        
                        // Notificações
                        .requestMatchers(HttpMethod.PUT, "/api/notifications").hasRole("ADMIN")
                        
                        // Produtos
                        .requestMatchers(HttpMethod.POST, "/api/products").hasRole("USER")
                        .requestMatchers(HttpMethod.PUT, "/api/products").hasRole("USER")
                        .requestMatchers(HttpMethod.GET, "/api/products").hasAnyRole("ADMIN", "USER")
                        
                        // Todas as outras requisições precisam de autenticação
                        .anyRequest().authenticated()
                )
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
