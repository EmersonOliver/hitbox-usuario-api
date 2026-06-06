package br.com.hitbox.core.usecase;

import br.com.hitbox.core.domain.Team;
import br.com.hitbox.core.gateway.CompanyGateway;
import br.com.hitbox.core.gateway.TeamGateway;
import br.com.hitbox.infra.exceptions.HitboxException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class TeamUseCase {

    private final TeamGateway teamGateway;
    private final CompanyGateway companyGateway;

    public Team createDefaultTeam(UUID companyId) {
        var company = companyGateway.findById(companyId).orElseThrow(() -> new HitboxException("Company não existe"));
        var team = Team.builder()
                .createdAt(LocalDateTime.now())
                .teamName(company.getCompanyName().concat("_team"))
                .companyId(companyId)
                .active(Boolean.TRUE)
                .build();
        return teamGateway.save(team);
    }
}
