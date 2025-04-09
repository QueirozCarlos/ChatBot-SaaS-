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
        Filter SecurityFilter;
        return httpSecurity
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize

                                //Acesso público
                                .requestMatchers(HttpMethod.POST, "/api/auth/login").permitAll()
                                .requestMatchers(HttpMethod.POST, "/api/auth/register").permitAll()

                                // Acesso ADMIN total a essas rotas
                                .requestMatchers("/api/products").hasRole("ADMIN")
                                .requestMatchers("/api/sales").hasRole("ADMIN")
                                .requestMatchers("/api/reports").hasRole("ADMIN")
                                .requestMatchers("/api/users").hasRole("ADMIN")

                                // Notificações - ADMIN pode criar/editar, USER pode ler
                                .requestMatchers(HttpMethod.POST, "/api/notifications").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.PUT, "/api/notifications").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.GET, "/api/notifications").hasAnyRole("ADMIN", "USER")

                                // Sales e produtos - ADMIN pode criar/editarrr
                                .requestMatchers(HttpMethod.POST, "/api/products").hasRole("USER")
                                .requestMatchers(HttpMethod.PUT, "/api/sales").hasRole("USER")
                                // Qualquer outra rota exige autenticação

                                // Libera o acesso ao console do H2
                                .requestMatchers("/h2-console/login.do").permitAll()

                                .anyRequest().authenticated()


//                        .requestMatchers(HttpMethod.POST, "/api/auth/login").permitAll() //qualquer pessoa faz requisição ao endpoint
//                        .requestMatchers(HttpMethod.POST, "/api/auth/register").permitAll() //qualquer pessoa faz requisição ao endpoint
//                        .requestMatchers(HttpMethod.POST, "/api/products").hasRole("ADMIN")
//                        .anyRequest().authenticated()
                )
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    //codificar(hash) senha de forma segura
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
