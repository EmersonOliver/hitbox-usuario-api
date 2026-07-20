package br.com.hitbox.core.domain;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NotificationRead {

    private UUID notificationId;
    private UUID companyId;
    private UUID userId;
    private Boolean read;
}
