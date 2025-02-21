package com.myproject.reserva_restaurantes.config;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;


@Component
public class JwtUtil {
    @Value("${jwt.secret}")
    private String SECRET_API;


    public String generateToken(String username) {
        return JWT.create()
                .withSubject(username)
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + 864000000)) // 10 dias
                .sign(Algorithm.HMAC256(SECRET_API.getBytes()));
    }

    public String getUsername(String token) {
        return JWT.require(Algorithm.HMAC256(SECRET_API.getBytes()))
                .build()
                .verify(token)
                .getSubject();
    }

    public boolean isValid(String token) {
        try {
            JWT.require(Algorithm.HMAC256(SECRET_API.getBytes()))
                    .build()
                    .verify(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }


}

