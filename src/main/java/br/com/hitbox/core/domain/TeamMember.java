package br.com.hitbox.core.domain;

import br.com.hitbox.infra.enums.TeamRole;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class TeamMember {

    private UUID memberId;

    private TeamRole role;

    private LocalDateTime joinedAt;

    public void changeRole(TeamRole role) {
        this.role = role;
    }

}
