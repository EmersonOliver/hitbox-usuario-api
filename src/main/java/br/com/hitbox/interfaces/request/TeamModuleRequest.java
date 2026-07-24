package br.com.hitbox.interfaces.request;
import lombok.*;

import java.util.List;
import java.util.UUID;
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TeamModuleRequest {
    private UUID teamId;
    private List<TeamModulePermissionRequest> teamPermissions;
}
