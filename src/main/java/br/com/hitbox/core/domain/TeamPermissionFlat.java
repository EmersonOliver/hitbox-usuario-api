package br.com.hitbox.core.domain;

import lombok.*;

import java.util.UUID;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TeamPermissionFlat {

    private UUID moduleId;
    private String moduleCode;
    private String moduleName;

    private UUID permissionId;
    private String permissionCode;
    private String permissionName;

    private UUID teamPermissionId;
    private UUID teamId;
}
