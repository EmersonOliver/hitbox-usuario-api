package br.com.hitbox.infra.service;

import br.com.hitbox.core.domain.User;
import br.com.hitbox.infra.entity.UserEntity;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;


@Slf4j
@Service
@RequiredArgsConstructor
public class TokenService {

    @Value("${secret.key.api}")
    private String secret;

    public String generateToken(UserEntity user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            String token = JWT.create().withIssuer("erp-hitbox")
                    .withSubject(user.getId().toString())
                    .withExpiresAt(generateExpirationDate())
                    .sign(algorithm);
            log.info("Token generate Successfully");
            return token;
        } catch (RuntimeException e) {
            log.error("Error generate token {}", e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    public String validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm).withIssuer("erp-servicos")
                    .build()
                    .verify(token).getSubject();

        } catch (JWTVerificationException e) {
            return "";
        }
    }

    private Instant generateExpirationDate() {
        return LocalDateTime.now().plusHours(1)
                .toInstant(ZoneOffset.of("-03:00"));
    }
}
