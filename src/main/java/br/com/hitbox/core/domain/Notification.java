package br.com.hitbox.core.domain;

import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Notification {
    private UUID notificationId;
    private UUID companyId;
    private String type;
    private String title;
    private String message;
    private Boolean read;
    private LocalDateTime createAt;

}
