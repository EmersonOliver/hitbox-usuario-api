package br.com.hitbox.security;

import br.com.hitbox.infra.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class AuthenticatedUser {
    private UUID userId;
    private UUID companyId;
    private String email;
    private UserRole role;

    public boolean hasCompany() {
        return companyId != null;
    }

    public boolean isOwner() {
        return role == UserRole.OWNER;
    }

    public boolean isOnboardingPending() {
        return companyId == null;
    }
}
