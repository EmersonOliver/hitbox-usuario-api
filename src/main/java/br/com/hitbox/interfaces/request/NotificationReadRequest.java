package br.com.hitbox.interfaces.request;

import java.util.UUID;

public record NotificationReadRequest(
         UUID notificationId,
         UUID companyId,
         UUID userId,
         Boolean read
) {
}
