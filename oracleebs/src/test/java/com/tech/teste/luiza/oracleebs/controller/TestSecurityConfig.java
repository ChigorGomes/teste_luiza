package com.tech.teste.luiza.oracleebs.controller;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;


/**
 * Configuração de segurança para testes.
 *
 * <p>Define uma configuração simplificada de segurança desabilitando o CSRF
 * e permitindo todas as requisições sem autenticação.</p>
 *
 * @author Cicero Higor Gomes
 */

@TestConfiguration
public class TestSecurityConfig {
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth.anyRequest().permitAll());

        return http.build();
    }

}
