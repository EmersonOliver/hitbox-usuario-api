package br.com.hitbox.infra.mapper;

import br.com.hitbox.core.aggregate.TeamPermissionAggregate;
import br.com.hitbox.core.domain.TeamPermissionFlat;
import br.com.hitbox.core.domain.TeamPermissionItem;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class TeamPermissionAggregateMapper {

    public List<TeamPermissionAggregate> toAggregate(List<TeamPermissionFlat> flat) {

//        Map<String, List<TeamPermissionFlat>> groupedModules =
//                flat.stream()
//                        .collect(Collectors.groupingBy(
//                                TeamPermissionFlat::getModuleCode
//                        ));
        Map<UUID, List<TeamPermissionFlat>> groupedModules =
                flat.stream()
                        .collect(Collectors.groupingBy(
                                TeamPermissionFlat::getModuleId
                        ));
        return groupedModules.values()
                .stream()
                .map(this::buildModule)
                .toList();
    }

    private TeamPermissionAggregate buildModule(List<TeamPermissionFlat> permissions) {

        TeamPermissionFlat module =
                permissions.getFirst();

        List<TeamPermissionItem> permissionItems =
                permissions.stream()
                        .map(permission ->
                                TeamPermissionItem.builder()
                                        .permissionId(permission.getPermissionId())
                                        .permissionCode(permission.getPermissionCode())
                                        .permissionName(permission.getPermissionName())
                                        .teamPermissionId(permission.getTeamPermissionId())
                                        .granted(permission.getTeamPermissionId() != null)
                                        .build()
                        )
                        .toList();

        return TeamPermissionAggregate.builder()
                .moduleId(module.getModuleId())
                .moduleCode(module.getModuleCode())
                .moduleName(module.getModuleName())
                .permissions(permissionItems)
                .build();
    }
}
