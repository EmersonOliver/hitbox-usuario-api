package br.com.hitbox.core.gateway;

import br.com.hitbox.core.domain.TeamPermission;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface TeamPermissionGateway {

    List<TeamPermission> findByTeamId(UUID teamId);

    Set<String> findPermissionCodesByTeamId(
            UUID teamId
    );
}
