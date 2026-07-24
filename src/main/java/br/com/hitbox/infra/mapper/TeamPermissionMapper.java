package br.com.hitbox.infra.mapper;

import br.com.hitbox.core.domain.TeamPermissionFlat;
import br.com.hitbox.infra.query.projection.TeamPermissionProjection;
import org.springframework.stereotype.Component;

@Component
public class TeamPermissionMapper {

    public TeamPermissionFlat toDomain(TeamPermissionProjection projection) {

        return TeamPermissionFlat.builder()
                .moduleId(projection.getModuleId())
                .moduleCode(projection.getModuleCode())
                .moduleName(projection.getModuleName())
                .permissionId(projection.getPermissionId())
                .permissionCode(projection.getPermissionCode())
                .permissionName(projection.getPermissionName())
                .teamPermissionId(projection.getTeamPermissionId())
                .teamId(projection.getTeamId())
                .build();
    }
}
