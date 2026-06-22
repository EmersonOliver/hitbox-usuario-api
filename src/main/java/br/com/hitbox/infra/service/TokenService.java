package br.com.hitbox.infra.service;

import br.com.hitbox.core.domain.Company;
import br.com.hitbox.core.domain.Team;
import br.com.hitbox.core.domain.User;
import br.com.hitbox.infra.entity.UserEntity;
import br.com.hitbox.infra.enums.TeamRole;
import br.com.hitbox.infra.enums.UserRole;
import br.com.hitbox.interfaces.request.CompanyMembershipRequest;
import br.com.hitbox.security.AuthenticatedUser;
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
import java.util.UUID;


@Slf4j
@Service
@RequiredArgsConstructor
public class TokenService {

    @Value("${secret.key.api}")
    private String secret;

    public String generateToken(AuthenticatedUser context) {
        try {
            Algorithm algorithm =
                    Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer("erp-hitbox")
                    .withSubject(
                            context.getUserId()
                                    .toString()
                    )
                    .withClaim(
                            "X-User-Role",
                            context.getUserRole()
                                    .name()
                    )
                    .withClaim(
                            "X-Team-Role",
                            context.getTeamRole()
                                    .name()
                    )
                    .withClaim(
                            "X-Company-Id",
                            context.getCompanyId()
                                    .toString()
                    )
                    .withClaim(
                            "X-Team-Id",
                            context.getTeamId()
                                    .toString()
                    )
                    .withClaim(
                            "companyName",
                            context.getCompanyName()
                    )
                    .withClaim(
                            "teamName",
                            context.getTeamName()
                    )
                    .withClaim(
                            "email",
                            context.getEmail()
                    )
                    .withClaim(
                            "fullName",
                            context.getFullName()
                    )
                    .withExpiresAt(
                            generateExpirationDate()
                    )

                    .sign(algorithm);

        } catch (Exception e) {
            log.error("Erro ao gerar token", e);
            throw e;
        }
    }

    public String generateInviteToken(UUID requestId, Company company,
                                      Team team, CompanyMembershipRequest request) {
        try {
            Algorithm algorithm =
                    Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer("erp-hitbox")
                    .withSubject(
                            requestId
                                    .toString()
                    )
                    .withClaim(
                            "X-Team-Role",
                            request.getRole()
                                    .name()
                    )
                    .withClaim(
                            "X-Company-Id",
                            company.getCompanyId()
                                    .toString()
                    )
                    .withClaim(
                            "X-Team-Id",
                            team.getTeamId()
                                    .toString()
                    )
                    .withClaim(
                            "companyName",
                            company.getCompanyName()
                    )
                    .withClaim(
                            "teamName",
                            team.getTeamName()
                    )
                    .withClaim(
                            "email",
                            request.getEmail()
                    )
                    .withExpiresAt(
                            LocalDateTime.now().plusHours(1)
                                    .toInstant(ZoneOffset.of("-03:00"))
                    )
                    .sign(algorithm);

        } catch (Exception e) {
            log.error("Erro ao gerar token", e);
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
                                    user.getRole().name()
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

    public String generateTemporaryToken(
            UserEntity user
    ) {

        Algorithm algorithm =
                Algorithm.HMAC256(secret);

        return JWT.create()
                .withIssuer("erp-hitbox")
                .withSubject(
                        user.getId().toString()
                )
                .withClaim(
                        "X-User-Id",
                        user.getId().toString()
                )
                .withClaim(
                        "X-User-Role",
                        user.getRole().name()
                )
                .withClaim(
                        "temporary",
                        true
                )
                .withExpiresAt(
                        generateExpirationDate()
                )
                .sign(algorithm);
    }

    public UUID getUserId(String token) {
        var decoded =
                JWT.require(
                                Algorithm.HMAC256(secret)
                        )
                        .withIssuer("erp-hitbox")
                        .build()
                        .verify(token);

        String userId =
                decoded.getClaim(
                        "sub"
                ).asString();

        return userId != null
                ? UUID.fromString(userId)
                : null;
    }

    public UUID getCompanyId(String token) {
        var decoded =
                JWT.require(
                                Algorithm.HMAC256(secret)
                        )
                        .withIssuer("erp-hitbox")
                        .build()
                        .verify(token);

        String companyId =
                decoded.getClaim(
                        "X-Company-Id"
                ).asString();

        return companyId != null
                ? UUID.fromString(companyId)
                : null;
    }

    public UUID getTeamId(String token) {
        var decoded =
                JWT.require(
                                Algorithm.HMAC256(secret)
                        )
                        .withIssuer("erp-hitbox")
                        .build()
                        .verify(token);

        String teamId =
                decoded.getClaim(
                        "X-Team-Id"
                ).asString();

        return teamId != null
                ? UUID.fromString(teamId)
                : null;
    }

    public String getEmail(String token) {
        var decoded =
                JWT.require(
                                Algorithm.HMAC256(secret)
                        )
                        .withIssuer("erp-hitbox")
                        .build()
                        .verify(token);

        return decoded.getClaim(
                "email"
        ).asString();
    }

    public String getCompanyName(String token) {
        var decoded =
                JWT.require(
                                Algorithm.HMAC256(secret)
                        )
                        .withIssuer("erp-hitbox")
                        .build()
                        .verify(token);

        return decoded.getClaim(
                "companyName"
        ).asString();
    }

    public String getTeamName(String token) {
        var decoded =
                JWT.require(
                                Algorithm.HMAC256(secret)
                        )
                        .withIssuer("erp-hitbox")
                        .build()
                        .verify(token);

        return decoded.getClaim(
                "teamName"
        ).asString();
    }

    public UserRole getUserRole(String token) {
        var decoded =
                JWT.require(
                                Algorithm.HMAC256(secret)
                        )
                        .withIssuer("erp-hitbox")
                        .build()
                        .verify(token);

        String userRole =
                decoded.getClaim(
                        "X-User-Role"
                ).asString();

        return userRole != null
                ? UserRole.valueOf(userRole)
                : null;
    }

    public TeamRole getTeamRole(String token) {
        var decoded =
                JWT.require(
                                Algorithm.HMAC256(secret)
                        )
                        .withIssuer("erp-hitbox")
                        .build()
                        .verify(token);

        String teamRole =
                decoded.getClaim(
                        "X-Team-Role"
                ).asString();

        return teamRole != null
                ? TeamRole.valueOf(teamRole)
                : null;
    }

    public String getFullName(String token) {
        var decoded =
                JWT.require(Algorithm.HMAC256(secret))
                        .withIssuer("erp-hitbox")
                        .build()
                        .verify(token);

        return decoded.getClaim(
                "fullName"
        ).asString();
    }
}
