package com.university.api.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.university.api.domain.usuarios.Usuario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${app.api.secret}")
    private String secret;

    public String generateToken(Usuario u){

        try{
            System.out.println(secret);
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer("auth0")
                    .withSubject(u.getUsername())
                    .withClaim("id", u.getId())
                    .withExpiresAt(generateExpiredDate())
                    .sign(algorithm);
        }catch (JWTCreationException e){
            throw new RuntimeException(e);
        }
    }

    private Instant generateExpiredDate(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-05:00"));
    }
}
