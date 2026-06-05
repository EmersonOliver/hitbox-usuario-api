package br.com.hitbox.core.gateway;

import br.com.hitbox.core.domain.User;
import br.com.hitbox.core.domain.UserProfile;

import java.util.Optional;
import java.util.UUID;

public interface UserGateway {

    User save(User domain);

    User update(User domain, UUID id);

    void deleteUser(UUID id);

    Optional<User> findByEmail(UUID companyId, String email);
    Optional<User> findByEmail( String email);

    Optional<UserProfile> findProfileById(UUID userId);

    Optional<User> findById(UUID userId);
}
