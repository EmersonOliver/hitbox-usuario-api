package br.com.hitbox.core.usecase;

import br.com.hitbox.core.domain.CompanyMembership;
import br.com.hitbox.core.gateway.CompanyMembershipGateway;
import br.com.hitbox.infra.enums.TeamRole;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class CompanyMembershipUseCase {

    private final CompanyMembershipGateway gateway;

    public CompanyMembership createOwnerMembership(
            UUID userId,
            UUID companyId,
            UUID teamId
    ) {

        CompanyMembership membership =
                CompanyMembership.builder()
                        .userId(userId)
                        .companyId(companyId)
                        .teamId(teamId)
                        .role(TeamRole.OWNER)
                        .active(true)
                        .joinedAt(LocalDateTime.now())
                        .build();

        return gateway.save(membership);
    }
}
