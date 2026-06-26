package br.com.hitbox.core.domain;

import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class TeamPermission {

    private UUID teamPermissionId;

    private UUID teamId;

    private UUID modulePermissionId;

    private String permissionCode;
}
