package br.com.hitbox.infra.service;

import br.com.hitbox.core.domain.Company;
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
            Algorithm algorithm =
                    Algorithm.HMAC256(secret);
            var builder =
                    JWT.create()
                            .withIssuer("erp-hitbox")
                            .withSubject(user.getId().toString())
                            .withClaim(
                                    "X-User-Role",
                                    "ROLE_" + user.getRole().name()
                            ).withClaim("name", user.getName())
                            .withClaim("fullName", user.getFullName())
                            .withClaim("email", user.getEmail())
                            .withClaim("companyName",
                                    user.getCompany() != null ? user.getCompany().getCompanyName() : null)
                            .withExpiresAt(
                                    generateExpirationDate()
                            );

            if (user.getCompany() != null) {

                builder.withClaim(
                        "X-Company-Id",
                        user.getCompany().getId().toString()
                );
            }

            return builder.sign(algorithm);

        } catch (RuntimeException e) {

            log.error(
                    "Error generate token {}",
                    e.getMessage(),
                    e
            );

            throw e;
        }
    }

    public String generateToken(User user, Company company) {
        try {
            Algorithm algorithm =
                    Algorithm.HMAC256(secret);
            var builder =
                    JWT.create()
                            .withIssuer("erp-hitbox")
                            .withSubject(user.getUserId().toString())
                            .withClaim(
                                    "X-User-Role",
                                    "ROLE_" + user.getRole().name()
                            ).withClaim("name", user.getName())
                            .withClaim("fullName", user.getFullName())
                            .withClaim("email", user.getEmail())
                            .withClaim("companyName",
                                    company.getCompanyName())
                            .withExpiresAt(
                                    generateExpirationDate()
                            );
            builder.withClaim(
                    "X-Company-Id",
                    company.getCompanyId().toString()
            );


            return builder.sign(algorithm);

        } catch (RuntimeException e) {

            log.error(
                    "Error generate token {}",
                    e.getMessage(),
                    e
            );

            throw e;
        }
    }

    public String validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm).withIssuer("erp-hitbox")
                    .build()
                    .verify(token).getSubject();

        } catch (JWTVerificationException e) {
            return "";
        }
    }

    private Instant generateExpirationDate() {
        return LocalDateTime.now().plusHours(24)
                .toInstant(ZoneOffset.of("-03:00"));
    }
}
