package br.com.hitbox.core.usecase;

import br.com.hitbox.core.domain.Company;
import br.com.hitbox.core.domain.CompanyMembership;
import br.com.hitbox.core.domain.Team;
import br.com.hitbox.core.domain.User;
import br.com.hitbox.core.gateway.CompanyMembershipGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class CompanyMembershipUseCase {

    private final CompanyMembershipGateway gateway;

    public CompanyMembership createOwnerMembership(
            User user,
            Company company,
            Team team
    ) {

        CompanyMembership membership =
                CompanyMembership.builder()
                        .userId(user.getUserId())
                        .companyId(company.getCompanyId())
                        .teamId(team.getTeamId())
                        .active(true)
                        .joinedAt(LocalDateTime.now())
                        .build();

        return gateway.save(membership);
    }
}
