package com.haberchid.crud_backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class SecurityConfig{

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
             httpSecurity
                     .csrf(AbstractHttpConfigurer::disable)
                     .cors(Customizer.withDefaults())
                     .authorizeHttpRequests(authorize ->
                             authorize.requestMatchers("/api/v1/auth/**").permitAll()
                                      .anyRequest().authenticated()
                     )
                     .sessionManagement(session ->
                             session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));


             return httpSecurity.build();
    }
}
