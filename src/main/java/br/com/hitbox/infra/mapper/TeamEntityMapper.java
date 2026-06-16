package br.com.hitbox.infra.mapper;

import br.com.hitbox.core.domain.Team;
import br.com.hitbox.infra.entity.CompanyEntity;
import br.com.hitbox.infra.entity.TeamEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TeamEntityMapper {

    private final UserEntityMapper userEntityMapper;

    public TeamEntity toEntity(Team domain) {
        return TeamEntity.builder()
                .id(domain.getCompanyId())
                .company(CompanyEntity.builder()
                        .id(domain.getCompanyId())
                        .build())
                .teamName(domain.getTeamName())
                .defaultTeam(Boolean.TRUE)
                .active(domain.getActive())
                .description(domain.getDescription())
                .build();
    }

    public Team toDomain(TeamEntity entity) {
        return Team.builder()
                .teamId(entity.getId())
                .createdAt(entity.getCreatedAt())
                .active(entity.getActive())
                .companyId(entity.getCompany().getId())
                .updatedAt(entity.getUpdatedAt())
                .teamName(entity.getTeamName())
                .description(entity.getDescription())
                .build();
    }

}
