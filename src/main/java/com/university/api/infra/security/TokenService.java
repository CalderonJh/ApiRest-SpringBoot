package com.university.api.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.university.api.domain.usuarios.admin.Admin;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${app.api.secret}")
    private String secret;


    public String generateToken(Admin u) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer("auth0")
                    .withSubject(u.getUsername())
                    .withClaim("id", u.getId())
//                    .withExpiresAt(generateExpiredDate())
                    .sign(algorithm);
        } catch (JWTCreationException e) {
            throw new RuntimeException("Generate token error", e);
        }
    }


    public String getSubject(String token) {
        if(token == null){
            throw new RuntimeException("getSubject error, token is null");
        }
        Algorithm algorithm = Algorithm.HMAC256(secret);
        DecodedJWT decoded;
        try {
            decoded = JWT.require(algorithm)
                    .withIssuer("auth0")
                    .build()
                    .verify(token);
            return decoded.getSubject();
        }catch (JWTVerificationException e){
            throw new RuntimeException("Authorization error");
        }
    }


    private Instant generateExpiredDate() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-05:00"));
    }
}
