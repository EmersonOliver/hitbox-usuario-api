package br.com.hitbox.interfaces.request;

import lombok.*;

import java.util.List;
import java.util.UUID;
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TeamModulePermissionRequest {
    private UUID moduleId;

    private String moduleCode;

    private String moduleName;

    private List<TeamPermissionItemRequest> permissions;
}
