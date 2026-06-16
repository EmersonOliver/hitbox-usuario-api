package br.com.hitbox.core.gateway;

import br.com.hitbox.core.domain.Team;

import java.util.Optional;
import java.util.UUID;

public interface TeamGateway {
    Team save(Team domain);

    Team update(Team team);

    Optional<Team> findById(UUID id);
}
