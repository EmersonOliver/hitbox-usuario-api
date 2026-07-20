package br.com.hitbox.infra.query.projection;

import java.util.UUID;

public interface UserNotifyProjection {

    UUID getUserId();
    UUID getCompanyId();
    String getName();
    String getCompanyName();
}
