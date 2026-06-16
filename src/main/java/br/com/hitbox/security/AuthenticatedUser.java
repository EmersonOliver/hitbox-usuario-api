package br.com.hitbox.security;

import br.com.hitbox.infra.enums.TeamRole;
import br.com.hitbox.infra.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class AuthenticatedUser {
    private UUID userId;

    private UUID companyId;

    private UUID teamId;

    private String companyName;

    private String teamName;

    private String email;

    private String fullName;

    private UserRole userRole;

    private TeamRole teamRole;

    public boolean isOwner() {
        return TeamRole.OWNER.equals(teamRole);
    }

    public boolean isManager() {
        return TeamRole.MANAGER.equals(teamRole);
    }

    public boolean isMember() {
        return TeamRole.MEMBER.equals(teamRole);
    }

    public boolean hasCompany() {
        return companyId != null;
    }
}
