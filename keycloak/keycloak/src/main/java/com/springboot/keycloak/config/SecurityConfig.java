package com.springboot.keycloak.config;

import com.springboot.keycloak.auth.JwtAuthConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthConverter jwtAuthConverter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth ->
                        auth
                                .anyRequest().authenticated()
                );

        http
                .oauth2ResourceServer(auth2 ->
                        auth2
                                .jwt(jwt -> {
                                    jwt.jwtAuthenticationConverter(jwtAuthConverter);
                                })
                );

        http
                .sessionManagement(sessionManagement ->
                        sessionManagement
                                .sessionCreationPolicy(STATELESS)
                );

        return http.build();
    }
}
