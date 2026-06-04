package br.com.hitbox.infra.persistence;

import br.com.hitbox.core.domain.User;
import br.com.hitbox.core.gateway.UserGateway;
import br.com.hitbox.infra.jpa.SpringDataUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class UserPersistenceRepository implements UserGateway {

    private final SpringDataUserRepository repository;

    @Override
    public User save(User domain) {
        return null;
    }

    @Override
    public User update(User domain, UUID id) {
        return null;
    }

    @Override
    public void deleteUser(UUID id) {

    }
}
