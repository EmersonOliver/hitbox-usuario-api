package br.com.hitbox.infra.query.projection;

import java.time.LocalDateTime;
import java.util.UUID;

public interface NotificationProjection {

    UUID getId();

    String getTitle();

    String getMessage();

    Boolean getRead();
    LocalDateTime getCreatedAt();
}
