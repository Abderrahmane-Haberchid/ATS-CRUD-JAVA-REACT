package com.haberchid.crud_backend.config;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.haberchid.crud_backend.dto.CredentialsDto;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
@RequiredArgsConstructor
public class UsernamePasswordAuthFilter extends OncePerRequestFilter {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    private final UserAuthenticationProvider userAuthenticationProvider;

    @Override
    protected void doFilterInternal(
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse,
            FilterChain filterChain) throws ServletException, IOException {

        if ("api/v1/auth/signIn".equals(httpServletRequest.getServletPath())
                && HttpMethod.POST.matches(httpServletRequest.getMethod())) {
            CredentialsDto credentialsDto = MAPPER.readValue(httpServletRequest.getInputStream(), CredentialsDto.class);

            try {
                SecurityContextHolder.getContext().setAuthentication(
                        userAuthenticationProvider.validateCredentials(credentialsDto));
            } catch (RuntimeException e) {
                SecurityContextHolder.clearContext();
                throw e;
            }
        }

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
