package com.haberchid.crud_backend.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.JWTVerifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtService {



    public String generateToken(UserDetails userDetails) {
        return JWT.create()
                .withSubject(userDetails.getUsername())
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + 1000000))
                .sign(Algorithm.HMAC256("secretKey"));
    }


    public String extractUsername(String token) {
        return getVerifier().verify(token).getSubject();
    }


    public boolean isTokenValid(String token, UserDetails userDetails) {
        try {
            String username = extractUsername(token);
            return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
        } catch (JWTVerificationException e) {
            return false;  // invalid signature or token expired
        }
    }


    private boolean isTokenExpired(String token) {
        Date expiration = getVerifier().verify(token).getExpiresAt();
        return expiration.before(new Date());
    }

    // Create JWT verifier instance
    private JWTVerifier getVerifier() {
        return JWT.require(Algorithm.HMAC256("secretKey")).build();
    }
}
