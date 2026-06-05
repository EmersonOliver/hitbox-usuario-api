package br.com.hitbox.core.domain;

import br.com.hitbox.infra.enums.UserRole;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserProfile {
    private UUID userId;

    private String fullName;

    private String email;

    private String avatarUrl;

    private UserRole role;

    private UUID companyId;

    private String companyName;

    private UUID teamId;

    private String teamName;

    private Boolean active;

    private LocalDateTime lastLogin;
}
