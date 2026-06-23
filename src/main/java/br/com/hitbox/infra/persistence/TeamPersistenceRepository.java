package br.com.hitbox.infra.persistence;

import br.com.hitbox.core.domain.Team;
import br.com.hitbox.core.gateway.TeamGateway;
import br.com.hitbox.infra.entity.CompanyEntity;
import br.com.hitbox.infra.entity.TeamEntity;
import br.com.hitbox.infra.jpa.SpringDataTeamRepository;
import br.com.hitbox.infra.mapper.TeamEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class TeamPersistenceRepository implements TeamGateway {

    private final SpringDataTeamRepository teamRepository;
    private final TeamEntityMapper mapper;

    @Override
    public Team save(Team domain) {
        TeamEntity teamEntity = TeamEntity.builder()
                .defaultTeam(Boolean.TRUE)
                .teamName(domain.getTeamName())
                .teamRole(domain.getTeamRole())
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
                .teamRole(domain.getTeamRole())
                .createdAt(persisted.getCreatedAt())
                .build();
    }

    @Override
    public Team update(Team team) {
        var teamEntity = mapper.toEntity(team);
        return mapper.toDomain(teamRepository.save(teamEntity));
    }

    @Override
    public Optional<Team> findById(UUID id) {
        return teamRepository.findById(id).map(mapper::toDomain);
    }
}
