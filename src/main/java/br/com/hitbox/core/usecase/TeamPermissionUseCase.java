package br.com.hitbox.core.usecase;

import br.com.hitbox.infra.entity.ModulePermissionEntity;
import br.com.hitbox.infra.entity.TeamEntity;
import br.com.hitbox.infra.entity.TeamPermissionEntity;
import br.com.hitbox.infra.jpa.SpringDataTeamPermissionRepository;
import br.com.hitbox.security.AuthenticatedUser;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TeamPermissionUseCase {

    private final SpringDataTeamPermissionRepository repository;

    private final RedisTemplate<String, AuthenticatedUser> redisTemplate;


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
}
