package br.com.hitbox.infra.persistence;

import br.com.hitbox.core.domain.Team;
import br.com.hitbox.core.gateway.TeamGateway;
import br.com.hitbox.infra.entity.CompanyEntity;
import br.com.hitbox.infra.entity.TeamEntity;
import br.com.hitbox.infra.jpa.SpringDataTeamRepository;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.http.parser.TE;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class TeamPersistenceRepository implements TeamGateway {

    private final SpringDataTeamRepository teamRepository;

    @Override

    public Team save(Team domain) {
        TeamEntity teamEntity = TeamEntity.builder()
                .defaultTeam(Boolean.TRUE)
                .teamName(domain.getTeamName())
                .active(domain.getActive())
                .company(CompanyEntity.builder().id(domain.getCompanyId()).build())
                .createdAt(domain.getCreatedAt())
                .build();
        var persisted = teamRepository.save(teamEntity);
        return Team.builder()
                .teamId(persisted.getId())
                .companyId(persisted.getCompany().getId())
                .teamName(persisted.getTeamName())
                .active(persisted.getActive())
                .createdAt(persisted.getCreatedAt())
                .build();
    }
}
