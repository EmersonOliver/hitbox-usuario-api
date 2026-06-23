package br.com.hitbox.interfaces.mapper;

import br.com.hitbox.core.domain.Team;
import br.com.hitbox.interfaces.request.TeamRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TeamMapper {

   public Team toDomain(TeamRequest request) {
        if (request == null) {
            return null;
        }
        Team.TeamBuilder team = Team.builder();
        team.companyId(request.getCompanyId());
        team.teamName(request.getTeamName());
        team.description(request.getDescription());
        team.active(request.getActive());
        team.teamRole(request.getTeamRole());
        return team.build();
    }

}
