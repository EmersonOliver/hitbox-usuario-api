package br.com.hitbox.security;

import br.com.hitbox.infra.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;
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


    private Set<String> permissions;

    public boolean hasCompany() {
        return companyId != null;
    }
}
