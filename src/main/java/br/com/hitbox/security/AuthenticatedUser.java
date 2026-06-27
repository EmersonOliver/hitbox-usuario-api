package br.com.hitbox.security;

import br.com.hitbox.infra.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.cache.annotation.Cacheable;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
//@Cacheable(
//        value = "user-context",
//        key = "#userId + ':' + #companyId"
//)
public class AuthenticatedUser implements Serializable {
    private UUID userId;

    private UUID companyId;

    private UUID teamId;

    private String companyName;

    private String teamName;

    private String email;

    private String fullName;

    private UserRole userRole;


    @Builder.Default
    private Set<String> permissions = new HashSet<>();

    public boolean hasCompany() {
        return companyId != null;
    }
}
