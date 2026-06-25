package br.com.hitbox.infra.persistence;

import br.com.hitbox.core.domain.TeamPermission;
import br.com.hitbox.core.gateway.TeamPermissionGateway;
import br.com.hitbox.infra.jpa.SpringDataTeamPermissionRepository;
import br.com.hitbox.infra.mapper.TeamEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class TeamPermissionGatewayImpl implements TeamPermissionGateway {

    private final SpringDataTeamPermissionRepository repository;
    private final TeamEntityMapper mapper;

    @Override
    public List<TeamPermission> findByTeamId(
            UUID teamId
    ) {

        return repository.findByTeamId(teamId)
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public Set<String> findPermissionCodesByTeamId(
            UUID teamId
    ) {

        return repository.findPermissionCodesByTeamId(
                teamId
        );
    }
}
