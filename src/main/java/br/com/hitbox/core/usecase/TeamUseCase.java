package br.com.hitbox.core.usecase;

import br.com.hitbox.core.domain.Team;
import br.com.hitbox.core.gateway.CompanyGateway;
import br.com.hitbox.core.gateway.TeamGateway;
import br.com.hitbox.infra.exceptions.HitboxException;
import br.com.hitbox.interfaces.mapper.TeamMapper;
import br.com.hitbox.interfaces.request.TeamRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class TeamUseCase {

    private final TeamGateway teamGateway;
    private final CompanyGateway companyGateway;
    private final TeamMapper teamMapper;

    public Team createDefaultTeam(UUID companyId) {
        var company = companyGateway.findById(companyId).orElseThrow(() -> new HitboxException("Company não existe"));
        var team = Team.builder()
                .createdAt(LocalDateTime.now())
                .teamName(company.getCompanyName().concat("_owners_team"))
                .companyId(companyId)
                .active(Boolean.TRUE)
                .build();
        return teamGateway.save(team);
    }

    public Team createTeam(UUID companyId, TeamRequest teamRequest) {
        var company = companyGateway.findById(companyId).orElseThrow(() -> new HitboxException("Company não existe"));
        var teamDomain = teamMapper.toDomain(teamRequest);
        teamDomain.setCreatedAt(LocalDateTime.now());
        teamDomain.setActive(Boolean.TRUE);
        teamDomain.setCompanyId(company.getCompanyId());
        return teamGateway.save(teamDomain);
    }

}
