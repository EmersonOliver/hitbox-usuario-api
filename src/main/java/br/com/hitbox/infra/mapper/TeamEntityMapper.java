package br.com.hitbox.infra.mapper;

import br.com.hitbox.core.domain.CompanyMembership;
import br.com.hitbox.core.domain.Team;
import br.com.hitbox.infra.entity.CompanyEntity;
import br.com.hitbox.infra.entity.TeamEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class TeamEntityMapper {

    private final UserEntityMapper userEntityMapper;

    public TeamEntity toEntity(Team domain) {
        return TeamEntity.builder()
                .id(domain.getTeamId())
                .company(CompanyEntity.builder()
                        .id(domain.getCompanyId())
                        .build())
                .teamName(domain.getTeamName())
                .description(domain.getDescription())
                .active(domain.getActive())
                .build();
    }

    public Team toDomain(TeamEntity entity) {
        List<CompanyMembership> memberships = new ArrayList<>();
        if (!Objects.isNull(entity.getMemberships()) && !entity.getMemberships().isEmpty()) {
            var membershipsEntityList = entity.getMemberships();
            membershipsEntityList.stream().map(rs ->
                    CompanyMembership.builder()
                            .role(rs.getRole())
                            .teamId(rs.getTeam().getId())
                            .companyName(rs.getCompany().getCompanyName())
                            .userId(rs.getUser().getId())
                            .membershipId(rs.getId())
                            .userEmail(rs.getUser().getEmail())
                            .userName(rs.getUser().getName())
                            .userLastname(rs.getUser().getLastname())
                            .joinedAt(rs.getJoinedAt())
                            .teamName(rs.getTeam().getTeamName())
                            .active(rs.getActive())
                            .build()).forEach(memberships::add);
        }
        return Team.builder()
                .teamId(entity.getId())
                .createdAt(entity.getCreatedAt())
                .active(entity.getActive())
                .companyId(entity.getCompany().getId())
                .updatedAt(entity.getUpdatedAt())
                .teamName(entity.getTeamName())
                .description(entity.getDescription())
                .memberships(
                        memberships
                )
                .build();
    }

}
