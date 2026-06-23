package br.com.hitbox.core.domain;

import br.com.hitbox.infra.enums.TeamRole;
import br.com.hitbox.infra.enums.UserRole;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompanyMembership {
    private UUID membershipId;
    private UUID userId;
    private UUID companyId;
    private UUID teamId;
    private String companyName;
    private String teamName;
    private TeamRole role;
    private LocalDateTime joinedAt;
    private Boolean active;
    /*
     * Dados de leitura
     */

    private String userName;

    private String userLastname;

    private String userEmail;

    public String getUserFullName() {

        return userLastname == null
                ? userName
                : userName + " " + userLastname;
    }

    public void changeRole(TeamRole role) {
        this.role = role;
    }

    public boolean isActive() {
        return Boolean.TRUE.equals(active);
    }

    public boolean isManager() {
        return TeamRole.MANAGER.equals(role);
    }

    public boolean isOwner() {
        return TeamRole.OWNER.equals(role);
    }
}
