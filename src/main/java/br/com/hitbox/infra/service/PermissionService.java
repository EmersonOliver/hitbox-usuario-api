package br.com.hitbox.infra.service;

import br.com.hitbox.core.domain.TeamPermission;
import br.com.hitbox.core.gateway.TeamPermissionGateway;
import br.com.hitbox.infra.entity.ModulePermissionEntity;
import br.com.hitbox.infra.entity.TeamEntity;
import br.com.hitbox.infra.entity.TeamPermissionEntity;
import br.com.hitbox.infra.jpa.SpringDataModulePermissionRepository;
import br.com.hitbox.infra.jpa.SpringDataTeamPermissionRepository;
import br.com.hitbox.infra.mapper.TeamEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PermissionService {

    private final TeamPermissionGateway gateway;

//    @Cacheable(
//            value = "team-permission-codes",
//            key = "#teamId"
//    )
    public Set<String> loadPermissions(
            UUID teamId
    ) {

        return gateway.findPermissionCodesByTeamId(
                teamId
        );
    }


}

