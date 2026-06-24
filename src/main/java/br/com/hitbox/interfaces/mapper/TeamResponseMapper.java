package br.com.hitbox.interfaces.mapper;

import br.com.hitbox.core.domain.CompanyMembership;
import br.com.hitbox.core.domain.Team;
import br.com.hitbox.interfaces.response.TeamMemberResponse;
import br.com.hitbox.interfaces.response.TeamResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
@RequiredArgsConstructor
public class TeamResponseMapper {

    public TeamResponse toResponse(
            Team team
    ) {

        return TeamResponse.builder()
                .teamId(team.getTeamId())
                .companyId(team.getCompanyId())
                .teamName(team.getTeamName())
                .description(team.getDescription())
                .active(team.getActive())
                .createdAt(team.getCreatedAt())
                .updatedAt(team.getUpdatedAt())

                .totalMembers(
                        team.countTotalMembers()
                )
                .members(
                        team.getMemberships() == null
                                ? Collections.emptyList()
                                : team.getMemberships()
                                  .stream()
                                  .map(this::toMemberResponse)
                                  .toList()
                )
                .build();
    }

    private TeamMemberResponse toMemberResponse(
            CompanyMembership membership
    ) {

        return TeamMemberResponse.builder()
                .membershipId(
                        membership.getMembershipId()
                )
                .userId(
                        membership.getUserId()
                )
                .name(
                        membership.getUserName()
                )
                .lastname(
                        membership.getUserLastname()
                )
                .fullName(
                        membership.getUserFullName()
                )
                .email(
                        membership.getUserEmail()
                )

                .active(
                        membership.getActive()
                )
                .joinedAt(
                        membership.getJoinedAt()
                )
                .build();
    }
}
