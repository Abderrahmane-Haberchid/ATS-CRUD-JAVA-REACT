package com.haberchid.crud_backend.config;

import com.haberchid.crud_backend.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig{

    private final UserRepository userRepository;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        JwtFilter jwtFilter = new JwtFilter(userDetailsService());
             httpSecurity
                     .csrf(AbstractHttpConfigurer::disable)
                     .cors(Customizer.withDefaults())
                     .authorizeHttpRequests(authorize ->
                             authorize.requestMatchers("/api/v1/auth/**").permitAll()
                                      .anyRequest().authenticated()
                     )
                     .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                     .sessionManagement(session ->
                             session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));


             return httpSecurity.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
    @Bean
    UserDetailsService userDetailsService() {
        return userRepository::findByEmail;
    }
}
