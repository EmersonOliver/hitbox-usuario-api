package br.com.hitbox.core.gateway;

import br.com.hitbox.core.domain.Notification;
import br.com.hitbox.core.domain.NotificationRead;

public interface NotificationGateway {

    Notification save(Notification domain);

    NotificationRead read(NotificationRead domain);
}
