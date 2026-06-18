package br.com.hitbox.infra.query;

import br.com.hitbox.core.domain.Team;
import br.com.hitbox.infra.entity.TeamEntity;
import br.com.hitbox.infra.jpa.SpringDataTeamRepository;
import br.com.hitbox.infra.mapper.TeamEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TeamQueryService {

    private final SpringDataTeamRepository teamRepository;
    private final TeamEntityMapper mapper;

    public Page<Team> listAllTeams(Pageable pageable, UUID companyId) {
        Specification<TeamEntity> specs = (root, query, builder) -> {
            return builder.equal(root.get("company").get("id"), companyId);
        };
        return teamRepository.findAll(specs, pageable).map(mapper::toDomain);
    }

}
