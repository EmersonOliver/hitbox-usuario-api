package br.com.hitbox.core.gateway;

import br.com.hitbox.core.domain.User;
import br.com.hitbox.core.domain.UserProfile;
import br.com.hitbox.infra.enums.UserRole;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserGateway {

    User save(User domain);

    User update(User domain, UUID id);

    void deleteUser(UUID id);

    Optional<User> findByEmail(UUID companyId, String email);

    Optional<User> findByEmail(String email);

    Optional<UserProfile> findProfileById(UUID userId);

    Optional<User> findById(UUID userId);

    List<User> loadByRolesAndCompany(UserRole userRole, UUID companyId);
}
