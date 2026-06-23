package br.com.hitbox.interfaces.request;

import br.com.hitbox.infra.enums.RequestStatus;
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
public class CompanyMembershipRequest {

    private UUID requestId;
    private UUID companyId;
    private UUID teamId;
    private String email;
    private TeamRole role;
    private UserRole userRole;
    private String invitationToken;
    private RequestStatus status;
    private LocalDateTime requestedAt;
    private LocalDateTime expiresAt;
}
