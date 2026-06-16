package br.com.hitbox.interfaces.mapper;

import br.com.hitbox.core.domain.Team;
import br.com.hitbox.interfaces.request.TeamRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TeamMapper {

    Team toDomain(TeamRequest request);
}
