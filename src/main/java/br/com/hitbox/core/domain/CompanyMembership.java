package br.com.hitbox.core.domain;

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
    private UserRole userRole;
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


    public boolean isActive() {
        return Boolean.TRUE.equals(active);
    }

}
