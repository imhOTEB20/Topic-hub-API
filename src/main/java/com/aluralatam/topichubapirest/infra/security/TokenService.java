package com.aluralatam.topichubapirest.infra.security;

import com.aluralatam.topichubapirest.domain.user.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;

import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${api.security.secret}")
    private String apiSecret;

    public String generateToken(User user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(apiSecret);
            return JWT.create()
                    .withIssuer("topic hub api rest")
                    .withSubject(user.getUsername())
                    .withClaim("id", user.getId())
                    .withExpiresAt(generateExpirationDate())
                    .sign(algorithm);
        } catch (JWTCreationException exception) {
            throw new RuntimeException();
        }
    }

    public String getSubject(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(apiSecret);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer("topic hub api rest")
                    .build();
            DecodedJWT decodedJWT =  verifier.verify(token);
            return decodedJWT.getSubject();
        } catch (JWTVerificationException exception) {
            System.out.println(exception.toString());
            throw new RuntimeException("invalid verifier.",exception);
        }
    }

    private Instant generateExpirationDate() {
        return LocalDateTime.now().plusHours(2)
                .toInstant(ZoneOffset.of("-05:00"));
    }
}
