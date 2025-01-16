package com.caingt.Forohub.api.infra.seguridad;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.caingt.Forohub.api.entorno.user.Usuario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${api.security.secret}")
    private String passwordSecret;

    private Instant generateDateExpires(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-05:00"));
    }

    public String generate(Usuario usuario){
        try {
            Algorithm algorithm = Algorithm.HMAC256(passwordSecret);
            return JWT.create()
                    .withIssuer("forum-api")
                    .withSubject(usuario.getEmail())
                    .withClaim("id", usuario.getId())
                    .withExpiresAt(generateDateExpires())
                    .sign(algorithm);
        } catch (JWTCreationException exception){
            throw new RuntimeException();
        }
    }

    public String getSubject(String token) {
        if (token == null || token.isEmpty()) {
            throw new RuntimeException("Token is empty or null");
        }
        DecodedJWT verifier = null;
        try {
            Algorithm algorithm = Algorithm.HMAC256(passwordSecret);
            verifier = JWT.require(algorithm)
                    .withIssuer("forum-api")
                    .build()
                    .verify(token);
        } catch (JWTVerificationException exception) {
            throw new RuntimeException("Invalid token: " + exception.getMessage(), exception);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Invalid token format: The input is not a valid base 64 encoded string.", e);
        }
        if (verifier == null || verifier.getSubject() == null) {
            throw new RuntimeException("Invalid or missing subject in token");
        }
        return verifier.getSubject();
    }

}
