package br.com.hitbox.interfaces.response;

import br.com.hitbox.infra.enums.UserRole;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TeamMemberResponse {
    private UUID membershipId;
    private UUID teamId;
    private UUID userId;
    private String name;
    private String lastname;
    private String fullName;
    private String email;
    private Boolean active;
    private UserRole role;
    private LocalDateTime joinedAt;
}
