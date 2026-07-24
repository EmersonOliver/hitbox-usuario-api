package br.com.hitbox.infra.query.projection;

import java.util.UUID;

public interface TeamPermissionProjection {
    UUID getModuleId();

    String getModuleCode();

    String getModuleName();

    UUID getPermissionId();

    String getPermissionCode();

    String getPermissionName();

    UUID getTeamPermissionId();

    UUID getTeamId();
}
