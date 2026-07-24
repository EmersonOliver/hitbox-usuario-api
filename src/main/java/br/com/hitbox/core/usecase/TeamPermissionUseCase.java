package br.com.hitbox.core.usecase;

import br.com.hitbox.core.aggregate.TeamPermissionAggregate;
import br.com.hitbox.core.domain.TeamPermission;
import br.com.hitbox.core.domain.TeamPermissionFlat;
import br.com.hitbox.core.domain.TeamPermissionItem;
import br.com.hitbox.core.gateway.TeamPermissionGateway;
import br.com.hitbox.infra.entity.ModulePermissionEntity;
import br.com.hitbox.infra.entity.TeamEntity;
import br.com.hitbox.infra.entity.TeamPermissionEntity;
import br.com.hitbox.infra.jpa.SpringDataModulePermissionRepository;
import br.com.hitbox.infra.jpa.SpringDataTeamPermissionRepository;
import br.com.hitbox.infra.mapper.TeamPermissionAggregateMapper;
import br.com.hitbox.infra.mapper.TeamPermissionMapper;
import br.com.hitbox.security.AuthenticatedUser;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TeamPermissionUseCase {

    private final SpringDataTeamPermissionRepository repository;
    private final SpringDataModulePermissionRepository permissionRepository;
    private final RedisTemplate<String, AuthenticatedUser> redisTemplate;
    private final TeamPermissionGateway teamPermissionGateway;
    private final TeamPermissionMapper mapper;
    private final TeamPermissionAggregateMapper aggregateMapper;


    @Transactional
    public void updatePermissions(
            UUID teamId,
            List<UUID> permissionIds
    ) {

        repository.deleteByTeam_Id(
                teamId
        );

        List<TeamPermissionEntity> entities =
                permissionIds.stream()
                        .map(permissionId ->
                                TeamPermissionEntity.builder()
                                        .team(
                                                TeamEntity.builder()
                                                        .id(teamId)
                                                        .build()
                                        )
                                        .permission(
                                                ModulePermissionEntity.builder()
                                                        .id(permissionId)
                                                        .build()
                                        )
                                        .build()
                        )
                        .toList();

        repository.saveAll(
                entities
        );

        redisTemplate.delete(
                "team-permission-codes::" + teamId
        );
    }

    @Transactional
    public void update(UUID teamId, List<TeamPermission> permissions) {
        List<TeamPermissionEntity> entities =
                new ArrayList<>();

        repository.deleteByTeam_Id(teamId);
        repository.flush();

        for (TeamPermission p : permissions) {

            if (p.getGranted()) {
                var teamPermissionEntity = TeamPermissionEntity.builder()
                        .team(
                                TeamEntity.builder()
                                        .id(teamId)
                                        .build()
                        )
                        .permission(
                                ModulePermissionEntity.builder()
                                        .id(p.getPermissionId())
                                        .build()
                        )
                        .build();
                entities.add(teamPermissionEntity);
            }
        }

        repository.saveAll(
                entities
        );
    }

    @Transactional
    public void grantAllPermissions(
            UUID teamId
    ) {

        var permissions =
                permissionRepository.findAll();

        var teamPermissions =
                permissions.stream()
                        .map(permission ->
                                TeamPermissionEntity.builder()
                                        .team(TeamEntity.builder()
                                                .id(teamId)
                                                .build())
                                        .permission(permission)
                                        .build()
                        )
                        .toList();

        repository.saveAll(
                teamPermissions
        );
    }

    public List<TeamPermissionAggregate> permissionsByTeam(UUID teamId) {

        List<TeamPermissionFlat> flat =
                repository.findPermissionsByTeam(teamId)
                        .stream()
                        .map(mapper::toDomain)
                        .toList();
        return aggregateMapper.toAggregate(flat);
    }


}
