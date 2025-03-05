package com.myproject.reserva_restaurantes.config;




import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {
    @Value("${jwt.secret}")
    private String SECRET_KEY;
    private final long EXPIRATION_TIME = 3600000; // 1 hora

    public String generateToken(String username) {
        return JWT.create()
        .withSubject(username)
        .withIssuedAt(new Date(System.currentTimeMillis()))
        .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
        .sign(Algorithm.HMAC256(SECRET_KEY));
    }

    public String verifyToken(String token) {
        return JWT.require(Algorithm.HMAC256(SECRET_KEY))
        .build()
        .verify(token)
        .getSubject();
    }

    public boolean isTokenValid(String token) {
        try {
            verifyToken(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
