package br.com.hitbox.core.usecase;

import br.com.hitbox.core.domain.User;
import br.com.hitbox.core.domain.UserProfile;
import br.com.hitbox.core.gateway.UserGateway;
import br.com.hitbox.infra.enums.UserRole;
import br.com.hitbox.infra.enums.UserStatus;
import br.com.hitbox.infra.exceptions.HitboxException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.Year;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class UserUseCase {

    private final UserGateway userGateway;
    private final PasswordEncoder passwordEncoder;

    public User createFirstLogin(User user) {
        userGateway.findByEmail(user.getEmail()).ifPresent(existing -> {
            throw new HitboxException("Já existe um usuário com este email.");
        });
        user.setPassword(passwordEncoder.encode(
                user.getPassword()
        ));
        user.setActive(true);
        user.setEmailVerified(false);
        user.setFirstLogin(true);
        user.setRole(UserRole.ADMIN);
        user.setStatus(UserStatus.ACTIVE);
        return userGateway.save(user);
    }

    public User create(User user) {
        userGateway.findByEmail(
                        user.getCompanyId(),
                        user.getEmail()
                )
                .ifPresent(existing -> {
                    throw new HitboxException(
                            "Já existe um usuário com este email."
                    );
                });
        user.setPassword(
                passwordEncoder.encode(
                        user.getPassword()
                )
        );
        user.setActive(true);
        user.setEmailVerified(false);
        user.setFirstLogin(true);
        return userGateway.save(user);
    }

    public UserProfile findById(UUID userId) {
        return userGateway.findProfileById(userId)
                .orElseThrow(() ->
                        new HitboxException(
                                "Usuário não encontrado."
                        ));
    }

    public void changePassword(
            UUID userId,
            String currentPassword,
            String newPassword
    ) {

        User user = userGateway.findById(userId)
                .orElseThrow(() ->
                        new HitboxException(
                                "Usuário não encontrado."
                        ));

        if (!passwordEncoder.matches(
                currentPassword,
                user.getPassword())) {

            throw new HitboxException(
                    "Senha atual inválida."
            );
        }

        user.setPassword(
                passwordEncoder.encode(
                        newPassword
                )
        );

        user.setFirstLogin(false);
        userGateway.save(user);
    }

    public String resetPassword(UUID userId) {
        User user = userGateway.findById(userId)
                .orElseThrow(() ->
                        new HitboxException(
                                "Usuário não encontrado."
                        ));

        String temporaryPassword = user.getName().concat("@").concat(Year.now().toString());
        user.setPassword(
                passwordEncoder.encode(
                        user.getPassword()
                )
        );
        user.setFirstLogin(true);
        userGateway.save(user);
        return temporaryPassword;
    }

    public void activate(UUID userId) {

        User user = userGateway.findById(userId)
                .orElseThrow(() ->
                        new HitboxException(
                                "Usuário não encontrado."
                        ));

        user.setActive(true);

        userGateway.save(user);
    }

    public void deactivate(UUID userId) {

        User user = userGateway.findById(userId)
                .orElseThrow(() ->
                        new HitboxException(
                                "Usuário não encontrado."
                        ));

        user.setActive(false);

        userGateway.save(user);
    }
}
