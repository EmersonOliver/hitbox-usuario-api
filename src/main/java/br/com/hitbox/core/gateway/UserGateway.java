package br.com.hitbox.core.gateway;

import br.com.hitbox.core.domain.User;

import java.util.UUID;

public interface UserGateway {

    User save(User domain);

    User update(User domain, UUID id);

    void deleteUser(UUID id);
}
