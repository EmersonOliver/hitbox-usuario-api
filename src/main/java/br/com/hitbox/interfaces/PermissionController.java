package br.com.hitbox.interfaces;

import br.com.hitbox.core.aggregate.TeamPermissionAggregate;
import br.com.hitbox.core.usecase.TeamPermissionUseCase;
import br.com.hitbox.interfaces.mapper.TeamModulePermissionMapper;
import br.com.hitbox.interfaces.request.TeamModuleRequest;
import br.com.hitbox.security.AuthenticatedUser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("permissions")
@RequiredArgsConstructor
public class PermissionController {

    private final TeamPermissionUseCase teamPermissionUseCase;
    private final TeamModulePermissionMapper mapper;

    @GetMapping("byTeam/{teamId}")
    public ResponseEntity<List<TeamPermissionAggregate>> teamPermissions(
            @AuthenticationPrincipal AuthenticatedUser user,
            @PathVariable("teamId") UUID teamId) {
        var response = teamPermissionUseCase.permissionsByTeam(teamId);

        return ResponseEntity.ok(response);
    }

    @PutMapping("update")
    public ResponseEntity<Void> updatePermissions(@RequestBody TeamModuleRequest request) {
        var res = mapper.toDomain(request);
        teamPermissionUseCase.update(request.getTeamId(), res);
        return ResponseEntity.ok().build();
    }
}
