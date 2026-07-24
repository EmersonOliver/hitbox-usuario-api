package br.com.hitbox.core.domain;

import lombok.*;

import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TeamPermissionItem {

    private UUID permissionId;

    private String permissionCode;

    private String permissionName;

    private UUID teamPermissionId;

    private Boolean granted;
}
