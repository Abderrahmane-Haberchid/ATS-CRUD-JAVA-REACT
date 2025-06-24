package com.haberchid.crud_backend.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.haberchid.crud_backend.dto.UserDto;
import com.haberchid.crud_backend.services.AuthenticationService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    @Value("${security.jwt.token.secret-key:secret-key}")
    private String secretKey;

    private final AuthenticationService authenticationService;
    private final UserAuthenticationProvider userAuthenticationProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest,
                                    HttpServletResponse httpServletResponse,
                                    FilterChain filterChain) throws ServletException, IOException {

        String header = httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION);

        if (header != null) {
            Algorithm algorithm = Algorithm.HMAC256(secretKey);

            JWTVerifier verifier = JWT.require(algorithm)
                    .build();

            DecodedJWT decoded = verifier.verify(header);

            UserDto user = authenticationService.findByEmail(decoded.getIssuer());

            return new UsernamePasswordAuthenticationToken(user, null, Collections.emptyList());
        }
        else return null;

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
