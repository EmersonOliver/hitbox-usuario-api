package br.com.hitbox.interfaces.mapper;

import br.com.hitbox.core.domain.TeamPermission;
import br.com.hitbox.interfaces.request.TeamModulePermissionRequest;
import br.com.hitbox.interfaces.request.TeamModuleRequest;
import br.com.hitbox.interfaces.request.TeamPermissionItemRequest;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TeamModulePermissionMapper {


    public List<TeamPermission> toDomain(TeamModuleRequest request){
        List<TeamPermission> teamPermissions = new ArrayList<>();
        if (request.getTeamPermissions() == null) {
            return teamPermissions;
        }

        for (TeamModulePermissionRequest module : request.getTeamPermissions()) {
            if (module.getPermissions() == null) {
                continue;
            }
            for (TeamPermissionItemRequest permission : module.getPermissions()) {

                teamPermissions.add(
                        TeamPermission.builder()
                                .teamPermissionId(permission.getTeamPermissionId())
                                .teamId(request.getTeamId())
                                .modulePermissionId(permission.getPermissionId())
                                .permissionCode(permission.getPermissionCode())
                                .granted(permission.getGranted())
                                .permissionId(permission.getPermissionId())
                                .build()
                );
            }
        }
        return teamPermissions;
    }
}
